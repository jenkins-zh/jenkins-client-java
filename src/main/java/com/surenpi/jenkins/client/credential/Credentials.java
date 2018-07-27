package com.surenpi.jenkins.client.credential;

import com.surenpi.jenkins.client.BaseManager;
import com.surenpi.jenkins.client.BaseModel;

import java.io.IOException;
import java.util.*;

/**
 * Jenkins Credentials Managers
 * @author suren
 */
public class Credentials extends BaseManager
{
    public static final String V1URL = "/credential-store/domain/_";
    public static final String V2URL = "/credentials/store/system/domain/_";

    private String baseUrl = V2URL;
    private boolean isVersion1 = false;

    public Credentials(){}

    public Credentials(String baseUrl) {
        this.baseUrl = baseUrl;
        if(V1URL.equals(baseUrl)){
            isVersion1 = true;
        }
    }

    /**
     * Create a credential<br/>
     * 创建一个凭据
     * @see #create(Credential, Boolean)
     * @param credential
     * @throws IOException
     */
    public void create(Credential credential) throws IOException
    {
        create(credential, isCrumb());
    }

    /**
     * Create credential then return with id<br/>
     * 创建并返回一个带有ID的凭据
     * @param credential
     * @return
     * @throws IOException
     */
    public Credential createAndFetch(Credential credential) throws IOException
    {
        String uuid = UUID.randomUUID().toString();

        credential.setId(null);
        credential.setDescription(uuid);

        create(credential, isCrumb());

        Map<String, Credential> credentialsMap = list();
        Collection<Credential> credentials = credentialsMap.values();
        for(Credential item : credentials)
        {
            if(uuid.equals(item.getDescription()))
            {
                return item;
            }
        }

        return null;
    }

    /**
     * 创建凭据
     * @param credential
     * @param crumbFlag
     * @throws IOException
     */
    public void create(Credential credential, Boolean crumbFlag) throws IOException
    {
        String url = String.format("%s/%s?", this.baseUrl, "createCredentials");
        getClient().postFormJson(url, credential.dataForCreate(), crumbFlag);
    }

    /**
     * 列出所有的凭据
     * @return
     * @throws IOException
     */
    public Map<String, Credential> list() throws IOException {
        String url = String.format("%s?depth=2", this.baseUrl);
        if (this.isVersion1) {
            CredentialResponseV1 response = getClient().get(url, CredentialResponseV1.class);
            Map<String, Credential> credentials = response.getCredentials();
            //need to set the id on the credentials as it is not returned in the body
            for (String crendentialId : credentials.keySet()) {
                credentials.get(crendentialId).setId(crendentialId);
            }
            return credentials;
        } else {
            CredentialResponse response = getClient().get(url, CredentialResponse.class);
            List<Credential> credentials = response.getCredentials();
            Map<String, Credential> credentialMap = new HashMap<>();
            for(Credential credential : credentials) {
                credentialMap.put(credential.getId(), credential);
            }
            return credentialMap;
        }
    }

    /**
     * Check target credential is exists by id.
     * @param credentialId
     * @return
     * @throws IOException
     */
    public boolean exists(String credentialId) throws IOException
    {
        if(credentialId == null)
        {
            return false;
        }

        Map<String, Credential> credentialMap = list();
        Iterator<String> it = credentialMap.keySet().iterator();
        while(it.hasNext())
        {
            String key = it.next();

            if(credentialId.equals(credentialMap.get(key).getId()))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * 根据ID来更新一个凭据
     * @see #update(String, Credential, Boolean)
     * @param credentialId
     * @param credential
     * @throws IOException
     */
    public void update(String credentialId, Credential credential) throws IOException
    {
        update(credentialId, credential, isCrumb());
    }

    /**
     * Update an existing credential.
     * @param credentialId the id of the credential to update
     * @param credential the credential to update
     * @param crumbFlag
     * @throws IOException
     */
    public void update(String credentialId, Credential credential, Boolean crumbFlag) throws IOException
    {
        credential.setId(credentialId);
        String url = String.format("%s/%s/%s/%s?", this.baseUrl, "credential", credentialId, "updateSubmit");
        getClient().postFormJson(url, credential.dataForUpdate(), crumbFlag);
    }

    /**
     * 根据ID删除一个凭据
     * @see #delete(String, Boolean)
     * @param credentialId
     * @throws IOException
     */
    public void delete(String credentialId) throws IOException
    {
        delete(credentialId, isCrumb());
    }

    /**
     * Delete the credential with the given id
     * @param credentialId the id of the credential
     * @param crumbFlag
     * @throws IOException
     */
    public void delete(String credentialId, Boolean crumbFlag) throws IOException
    {
        String url = String.format("%s/%s/%s/%s?", this.baseUrl, "credential", credentialId, "doDelete");
        getClient().postForm(url, new HashMap<String, String>(), crumbFlag);
    }

    /**
     * Represents the list response from Jenkins with the 2.x credentials plugin
     */
    public static class CredentialResponse extends BaseModel {
        private List<Credential> credentials;

        public void setCredentials(List<Credential> credentials) {
            this.credentials = credentials;
        }

        public List<Credential> getCredentials() {
            return credentials;
        }
    }

    /**
     * Represents the list response from Jenkins with the 1.x credentials plugin
     */
    public static class CredentialResponseV1 extends BaseModel {

        private Map<String, Credential> credentials;

        public Map<String, Credential> getCredentials() {
            return credentials;
        }

        public void setCredentials(Map<String, Credential> credentials) {
            this.credentials = credentials;
        }

    }

    @Override
    protected String[] getDependencyArray()
    {
        return new String[0];
    }
}