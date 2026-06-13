package com.omrbranch.stepdefinition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.omrbranch.changeprofile.ChangeProfilePic_Output_Pojo;
import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalDatas;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC003_ChangeProfileStep extends BaseClass {
	Response response;
	ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo;

	@Given("User sets bearer authorization using the saved logtoken for profile picture endpoint")
	public void userSetsBearerAuthorizationUsingTheSavedLogtokenForProfilePictureEndpoint() {

		initRequest();
		List<Header> lstheader = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "multipart/form-data");
		Header h3 = new Header("Authorization", "Bearer " + GlobalDatas.getGlobalDataInstance().getLogtoken());
		lstheader.add(h1);
		lstheader.add(h2);
		lstheader.add(h3);
		Headers headers = new Headers(lstheader);
		addHeaders(headers);
	}

	@Given("User sets multipart request body with valid image file for profile update")
	public void userSetsMultipartRequestBodyWithValidImageFileForProfileUpdate() {
		File file = new File(
				"C:\\Users\\ADMIN\\eclipse_api-testing\\OmrBranchAPIProject_Richie\\image\\Artboard_Copy_16.png");
		addMultiPartFormData("profile_picture", file);

	}

	@When("User sends {string} request to the ChangeProfile endpoint")
	public void userSendsRequestToTheChangeProfileEndpoint(String type) {
		response = sendRequest(type, Endpoints.CHANGEPROFILE);
		changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);
		int status = changeProfilePic_Output_Pojo.getStatus();
		getResponseBody(response);
		GlobalDatas.getGlobalDataInstance().setStatusCode(status);

	}

	@Then("User should verify the response message is {string}")
	public void userShouldVerifyTheResponseMessageIs(String expMessge) {
		String actMessage = changeProfilePic_Output_Pojo.getMessage();
		Assert.assertEquals("Verify The Response Message", actMessage, expMessge);
	}

}
