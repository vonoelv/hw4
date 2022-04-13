package com.example.tests;

import com.codeborne.selenide.Configuration;
import com.example.pages.RegistrationFormPage;
import com.example.utils.RandomDataGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RegistrationFormWithPageObjectsTests {
    RandomDataGenerator generator = new RandomDataGenerator();
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    String firstName = generator.getFirstName(),
            lastName = generator.getLastName(),
            userEmail = generator.getEmail(),
            gender = generator.getGender(),
            userNumber = generator.getPhoneNumber(),
            dayOfBirth = generator.getDay(),
            monthOfBirth = generator.getMonth(),
            yearOfBirth = generator.getYear(),
            dateOfBirth = String.format("%s %s,%s", dayOfBirth, monthOfBirth, yearOfBirth),
            subject = generator.getSubject(),
            hobby = generator.getHobby(),
            picturePath = "images/",
            pictureName = "fakeimage.png",
            currentAddress = generator.getAddress(),
            state = generator.getState(),
            city = generator.getCity(state);

    String resultTableName = "Student Name",
            resultTableEmail = "Student Email",
            resultTableGender = "Gender",
            resultTableMobile = "Mobile",
            resultTableDateOfBirth = "Date of Birth",
            resultTableSubjects = "Subjects",
            resultTableHobbies = "Hobbies",
            resultTablePicture = "Picture",
            resultTableAddress = "Address",
            resultTableStateAndCity = "State and City";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1200x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        System.out.println(subject);
        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture(picturePath + pictureName)
                .setAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .clickSubmit();

        registrationFormPage
                .checkResultTableHeaders()
                .checkResultTableField(resultTableName, firstName + " " + lastName)
                .checkResultTableField(resultTableEmail, userEmail)
                .checkResultTableField(resultTableGender, gender)
                .checkResultTableField(resultTableMobile, userNumber)
                .checkResultTableField(resultTableDateOfBirth, dateOfBirth)
                .checkResultTableField(resultTableSubjects, subject)
                .checkResultTableField(resultTableHobbies, hobby)
                .checkResultTableField(resultTablePicture, pictureName)
                .checkResultTableField(resultTableAddress, currentAddress)
                .checkResultTableField(resultTableStateAndCity, state + " " + city);
    }

}
