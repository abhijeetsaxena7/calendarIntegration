package com.libsys.calendar.google.authorization;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

@RestController
public class Controller {

	@Autowired
	OAuth2AuthorizedClientService clientService;

	@GetMapping("/")
	public String publicAcessMethod() {
		return "helloWorld";
	}

	@GetMapping("/restricted")
	public String restricted(OAuth2AuthenticationToken authenticationToken) {
		System.out.println();
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getName());
		client.getAccessToken().getTokenValue();
		client.getRefreshToken().getTokenValue();
		return "Premitted login";
	}

	public AuthorizationCodeFlow initializeCodeFlow() throws IOException {
		
		return new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(), new NetHttpTransport(), new JacksonFactory(), new GenericUrl("serverUrl for google authentication"), new BasicAuthentication("username", "password"), "clientId", "authorizationEncodeUrl").setCredentialDataStore(StoredCredential.getDefaultDataStore(new FileDataStoreFactory(new File("credentialFIle")))).build();
	}
}
