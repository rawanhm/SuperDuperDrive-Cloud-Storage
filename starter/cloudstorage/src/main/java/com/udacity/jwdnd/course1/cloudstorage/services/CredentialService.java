package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;


    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }
    public void createOrEditCredential(Credential credential) {
        String encodedKey = EncodedPassword();
        credential.setKey(encodedKey);
        String encryptedpassword = encryptionService.encryptValue(credential.getDecryptedpassword(), encodedKey);
        credential.setPassword(encryptedpassword);
        // credential id is not null for updating credentials
        if (credential.getCredentialid() != null) {
            credentialMapper.update(credential);
        } else {
            credentialMapper.insert(credential);

        }
    }
/*   public void EditCredential(Credential credential) {
        String encodedKey = EncodedPassword();
        credential.setKey(encodedKey);
        String encryptedPwd = encryptionService.encryptValue(credential.getDecryptedPassword(), encodedKey);
        credential.setPassword(encryptedPwd);
        // credential id is null for new credentials
        if (credential.getCredentialId() != null) {

            credentialMapper.update(credential);
        }
 }*/
    public List<Credential> getAllCredentials(Integer userid) {
        List<Credential> credentials = credentialMapper.getAllCredentials(userid);
        //decrypt password before display it
        decryptPassword(credentials);
        return credentials;
    }
    public void deleteCredential(Integer credentialid, Integer userid) {
        credentialMapper.delete(credentialid, userid);
    }

    //--------------------------helping methods --------------------------
    private static String EncodedPassword() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
       return Base64.getEncoder().encodeToString(key);
    }
    private void decryptPassword(List<Credential> credentials) {
        for (Credential credential : credentials) {
            String key = credential.getKey();
            String encryptedpassword = credential.getPassword();
            String decryptedpassword = encryptionService.decryptValue(encryptedpassword, key);
            credential.setDecryptedpassword(decryptedpassword);
        }}

}
