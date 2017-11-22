package com.surenpi.jenkins.client.credential;

import com.surenpi.jenkins.client.BaseManager;
import com.surenpi.jenkins.client.BaseModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jenkins Credentials Managers
 * @author suren
 */
public class Credentials extends BaseManager {
    public static final String V1URL = "/credential-store/domain/_";
    public static final String V2URL = "/credentials/store/system/domain/_";

    private String baseUrl = V2URL;
    private boolean isVersion1 = false;

    public void create(Credential credential) throws IOException {
        create(credential, false);
    }

    public void create(Credential credential, Boolean crumbFlag) throws IOException {
        String url = String.format("%s/%s?", this.baseUrl, "createCredentials");
        getClient().post_form_json(url, credential.dataForCreate(), crumbFlag);
    }

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

    public void update(String credentialId, Credential credential) throws IOException {
        update(credentialId, credential, false);
    }

    /**
     * Update an existing credential.
     * @param credentialId the id of the credential to update
     * @param credential the credential to update
     * @param crumbFlag
     * @throws IOException
     */
    public void update(String credentialId, Credential credential, Boolean crumbFlag) throws IOException {
        credential.setId(credentialId);
        String url = String.format("%s/%s/%s/%s?", this.baseUrl, "credential", credentialId, "updateSubmit");
        getClient().post_form_json(url, credential.dataForUpdate(), crumbFlag);
    }

    public void delete(String credentialId) throws IOException {
        delete(credentialId, false);
    }

    /**
     * Delete the credential with the given id
     * @param credentialId the id of the credential
     * @param crumbFlag
     * @throws IOException
     */
    public void delete(String credentialId, Boolean crumbFlag) throws IOException {
        String url = String.format("%s/%s/%s/%s?", this.baseUrl, "credential", credentialId, "doDelete");
        getClient().post_form(url, new HashMap<String, String>(), crumbFlag);
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
}