package com.brutallyhonestmirror.e2e.stepdefinitions;

import com.brutallyhonestmirror.e2e.pages.MirrorPage;
import com.brutallyhonestmirror.e2e.pages.RegisterPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;

public class UiJourneySteps {

    private RegisterPage registerPage;
    private MirrorPage mirrorPage;

    @Given("the user is on the registration page")
    public void theUserIsOnTheRegistrationPage() {
        registerPage = new RegisterPage(Hooks.driver);
        registerPage.open();
    }

    @When("they register with a unique email and a valid password")
    public void theyRegisterWithAUniqueEmailAndAValidPassword() {
        String email = "uitest" + System.currentTimeMillis() + "@example.com";
        registerPage.register(email, "testpassword123");
    }

    @When("they submit an excuse about missing the gym")
    public void theySubmitAnExcuseAboutMissingTheGym() {
        mirrorPage = new MirrorPage(Hooks.driver);
        mirrorPage.submitExcuse("I keep saying I'll go to the gym tomorrow.");
    }

    @Then("they should see an AI-generated reflection")
    public void theyShouldSeeAnAiGeneratedReflection() {
        String reflectionText = mirrorPage.waitForReflection();
        assertFalse("Reflection text should not be empty", reflectionText.isEmpty());
    }
}