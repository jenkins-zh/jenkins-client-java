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

    public String create() {
        return null;
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