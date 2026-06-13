package com.omrbranch.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalDatas;
import com.omrbranch.pojo.adduseraddress.AddUserAddress_Input_Pojo;
import com.omrbranch.pojo.adduseraddress.AddUserAddress_Output_Pojo;
import com.omrbranch.pojo.citylist.CityList;
import com.omrbranch.pojo.citylist.CityList_Input_Pojo;
import com.omrbranch.pojo.citylist.CityList_Output_Pojo;
import com.omrbranch.pojo.deleteaddress.DeleteAddress_Input_Pojo;
import com.omrbranch.pojo.deleteaddress.DeleteAddress_Output_Pojo;
import com.omrbranch.pojo.getuseraddress.GetUserAddress_Output_Pojo;
import com.omrbranch.pojo.statelist.StateList;
import com.omrbranch.pojo.statelist.StateList_Output_Pojo;
import com.omrbranch.pojo.updateuseraddress.UpdateUserAddress_Input_Pojo;
import com.omrbranch.pojo.updateuseraddress.UpdateUserAddress_Output_Pojo;

import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC002_AddressStep extends BaseClass {
	Response response;

	@Given("User adds headers for StateList")
	public void user_adds_headers_for_state_list() {
		initRequest();
		addHeader("accept", "application/json");
	}

	@When("User sends {string} request to StateList endpoint")
	public void user_sends_request_to_state_list_endpoint(String type) {
		response = sendRequest(type, Endpoints.STATELIST);
		int statusCode = getStatusCode(response);
		GlobalDatas.getGlobalDataInstance().setStatusCode(statusCode);
		System.out.println(statusCode);
		getResponseBody(response);
	}

	@Then("User should verify the stateList response message matches {string} and save the state id")
	public void user_should_verify_the_state_list_response_message_matches_and_save_the_state_id(String stateName) {

		StateList_Output_Pojo stateList_Output_Pojo = response.as(StateList_Output_Pojo.class);
		ArrayList<StateList> getStateList = stateList_Output_Pojo.getData();
		for (StateList stateList : getStateList) {
			if (stateList.getName().equals(stateName)) {

				int stateId = stateList.getId();
				String txtStatusID = String.valueOf(stateId);
				GlobalDatas.getGlobalDataInstance().setTxtStatusID(txtStatusID);
				System.out.println("State ID is = " + stateId);

				Assert.assertEquals(stateName, stateList.getName());

				break;
			}

		}

	}

	@Given("User adds headers for CityList")
	public void user_adds_headers_for_city_list() {
		initRequest();
		List<Header> lstHeader = new ArrayList<Header>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");

		lstHeader.add(h1);
		lstHeader.add(h2);

		Headers headers = new Headers(lstHeader);

		addHeaders(headers);
	}

	@When("User adds request body with state id for city list")
	public void user_adds_request_body_with_state_id_for_city_list() {
		CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(
				GlobalDatas.getGlobalDataInstance().getTxtStatusID());
		System.out.println(GlobalDatas.getGlobalDataInstance().getTxtStatusID());
		addPayload(cityList_Input_Pojo);
	}

	@When("User sends {string} request to CityList endpoint")
	public void user_sends_request_to_city_list_endpoint(String type) {
		response = sendRequest(type, Endpoints.CITYLIST);
		int statusCode = getStatusCode(response);
		GlobalDatas.getGlobalDataInstance().setStatusCode(statusCode);
		System.out.println(statusCode);
		getResponseBody(response);
	}

	@Then("User should verify the cityList response message matches {string} and save the city id")
	public void user_should_verify_the_city_list_response_message_matches_and_save_the_city_id(String cityName) {

		CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);
		ArrayList<CityList> cityList = cityList_Output_Pojo.data;
		for (CityList cityNames : cityList) {
			if (cityNames.getName().equals(cityName)) {
				int cityID = cityNames.getId();
				String txtCityID = String.valueOf(cityID);
				GlobalDatas.getGlobalDataInstance().setCityID(txtCityID);
				Assert.assertEquals("Verify the City Name", cityNames.getName(), cityName);

			}

		}
	}

	@Given("User adds headers and bearer authorization for accessing address endpoints")
	public void user_adds_headers_and_bearer_authorization_for_accessing_address_endpoints() {
		initRequest();
		List<Header> lstheader = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("Authorization", "Bearer " + GlobalDatas.getGlobalDataInstance().getLogtoken());
		lstheader.add(h1);
		lstheader.add(h2);
		lstheader.add(h3);
		Headers headers = new Headers(lstheader);
		addHeaders(headers);
	}

	@When("User adds request body for add new address {string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
	public void user_adds_request_body_for_add_new_address(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
		AddUserAddress_Input_Pojo address_Input_Pojo = new AddUserAddress_Input_Pojo(first_name, last_name, mobile,
				apartment, Integer.parseInt(state), Integer.parseInt(city), Integer.parseInt(country), zipcode, address,
				address_type);

		addPayload(address_Input_Pojo);
		response = sendRequest("POST", Endpoints.ADDUSERADDRESS);

		GlobalDatas.getGlobalDataInstance().setStatusCode(response.getStatusCode());
		System.out.println(response.getStatusCode());

	}

	@Then("User sends {string} request to addUserAddress endpoint")
	public void user_sends_request_to_add_user_address_endpoint(String type) {
		response = sendRequest(type, Endpoints.ADDUSERADDRESS);

	}

	@Then("User should verify the addUserAddress response message matches {string} and save the address id")
	public void user_should_verify_the_add_user_address_response_message_matches_and_save_the_address_id(
			String expMessage) {
		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String ActMessage = addUserAddress_Output_Pojo.getMessage();
		int address_id = addUserAddress_Output_Pojo.getAddress_id();
		String txtAddressid = String.valueOf(address_id);
		System.out.println(txtAddressid);
		GlobalDatas.getGlobalDataInstance().setTxtAddressID(txtAddressid);
		Assert.assertEquals("Verify the Success message", ActMessage, expMessage);
	}

	@When("User adds request body to update address {string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
	public void user_adds_request_body_to_update_address(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
		UpdateUserAddress_Input_Pojo updateUseraddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(
				GlobalDatas.getGlobalDataInstance().getTxtAddressID(), first_name, last_name, mobile, apartment,
				Integer.parseInt(state), Integer.parseInt(city), Integer.parseInt(country), zipcode, address,
				address_type);

		addPayload(updateUseraddress_Input_Pojo);
	}

	@When("User sends {string} request to updateUserAddress endpoint")
	public void user_sends_request_to_update_user_address_endpoint(String type) {
		response = sendRequest(type, Endpoints.UPDATEUSERADDRESS);
		getResponseBody(response);
	}

	@Then("User should verify the updateUserAddress response message matches {string}")
	public void user_should_verify_the_update_user_address_response_message_matches(String expMessage) {

		UpdateUserAddress_Output_Pojo updateUserAddress_Output_Pojo = response.as(UpdateUserAddress_Output_Pojo.class);
		String actMessage = updateUserAddress_Output_Pojo.getMessage();
		getResponseBody(response);
		Assert.assertEquals("Verify the updated message", actMessage, expMessage);
	}

	@Given("User adds headers and bearer authorization for accessing get address endpoints")
	public void user_adds_headers_and_bearer_authorization_for_accessing_get_address_endpoints() {
		initRequest();
		List<Header> lstHeader = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + GlobalDatas.getGlobalDataInstance().getLogtoken());
		lstHeader.add(h1);
		lstHeader.add(h2);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
	}

	@When("User sends {string} request to GetUserAddress endpoint")
	public void user_sends_request_to_get_user_address_endpoint(String type) {
		response = sendRequest(type, Endpoints.GETUSERADDRESS);
		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);

		GlobalDatas.getGlobalDataInstance().setStatusCode(getUserAddress_Output_Pojo.getStatus());
	}

	@Then("User should verify the GetUserAddress response message matches {string}")
	public void user_should_verify_the_get_user_address_response_message_matches(String expMessage) {
		
		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		String ActMessage = getUserAddress_Output_Pojo.getMessage();
		System.out.println(ActMessage);
		Assert.assertEquals("Verify the Response message", expMessage,ActMessage);
	}

	@When("User adds request body with address id")
	public void user_adds_request_body_with_address_id() {
		DeleteAddress_Input_Pojo DeleteAddress_Input_Pojo=new DeleteAddress_Input_Pojo(GlobalDatas.getGlobalDataInstance().getTxtAddressID());
		
		addPayload(DeleteAddress_Input_Pojo);
		
	
		
	}

	@When("User sends {string} request to DeleteAddress endpoint")
	public void user_sends_request_to_delete_address_endpoint(String type) {
		response = sendRequest(type, Endpoints.DELETEUSERADDRESS);
		getResponseBody(response);
		DeleteAddress_Output_Pojo deleteAddress_Output_Pojo = response.as(DeleteAddress_Output_Pojo.class);
		int status = deleteAddress_Output_Pojo.getStatus();
		GlobalDatas.getGlobalDataInstance().setStatusCode(status);
		
	}

	@Then("User should verify the DeleteAddress response message matches {string}")
	public void user_should_verify_the_delete_address_response_message_matches(String expMessage) {
		DeleteAddress_Output_Pojo deleteAddress_Output_Pojo = response.as(DeleteAddress_Output_Pojo.class);
		String actMessage = deleteAddress_Output_Pojo.getMessage();
		Assert.assertEquals("verify the Confirmation message", actMessage,expMessage);
	}

}
