package de.scisertec.admin.survey.model.scisertec.view;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

public class SciSerTecSheetCsvHolderBean {

    Boolean question1;
    Boolean question2;

    Boolean question3_1;
    String question3_1_1;
    String question3_2_1;
    String question3_3_1;

    Integer question4_1;
    String question4_1_addition;

    Integer question5_1;
    String question5_1_addition;
    Integer question5_1_1;
    String question5_1_1_addition;

    Integer question6_1;
    Integer question6_1_1;
    String question6_1_1_addition;
    Integer question6_2_1;

    Boolean question7_1_a;
    Boolean question7_1_b;
    Boolean question7_1_c;
    Boolean question7_1_d;
    Boolean question7_1_e;
    String question7_1_addition;

    Integer question8_1;
    String question8_1_addition;

    Integer question9_1;
    String question9_1_addition;

    Boolean question10_1;



    Integer question3_2;
    String question3_1_2;

    Boolean question4_2_a;
    Boolean question4_2_b;
    Boolean question4_2_c;
    Boolean question4_2_d;
    Boolean question4_2_e;
    String question4_2_addition;

    Boolean question5_2_a;
    Boolean question5_2_b;
    Boolean question5_2_c;
    Boolean question5_2_c_a;
    Boolean question5_2_c_b;
    Boolean question5_2_c_c;
    Boolean question5_2_c_d;
    Boolean question5_2_c_e;
    Boolean question5_2_c_f;
    Boolean question5_2_c_g;
    String question5_2_c_addition;
    String question5_2_addition;

    String question6_2;

    Boolean question7_2_a;
    Boolean question7_2_b;
    Boolean question7_2_c;
    Boolean question7_2_d;
    Boolean question7_2_e;
    String question7_2_addition;

    Integer question8_2;
    Integer question8_1_2;
    String question8_1_2_addition;
    Integer question8_2_2;

    Boolean question9_2_a;
    Boolean question9_2_b;
    Boolean question9_2_c;
    Boolean question9_2_d;
    Boolean question9_2_e;
    String question9_2_addition;


    String questionA;
    Boolean questionB;

    String title;
    String firstName;
    String lastName;

    String street;
    String zipCode;
    String city;
    String country;

    String email;
    String phone;
    String fax;

    public SciSerTecSheetCsvHolderBean(Sheet sheet) {
        SheetData question1 = sheet.data().get("question1");
        if(question1 != null) {
            this.question1 = (Boolean) question1.data();
        }
        SheetData question2 = sheet.data().get("question2");
        if(question2 != null) {
            this.question2 = (Boolean) question2.data();
        }
        SheetData question3_1 = sheet.data().get("question3_1");
        if(question3_1 != null) {
            this.question3_1 = (Boolean) question3_1.data();
        }
        SheetData question3_1_1 = sheet.data().get("question3_1_1");
        if(question3_1_1 != null) {
            this.question3_1_1 = (String) question3_1_1.data();
        }
        SheetData question3_2_1 = sheet.data().get("question3_2_1");
        if(question3_2_1 != null) {
            this.question3_2_1 = (String) question3_2_1.data();
        }
        SheetData question3_3_1 = sheet.data().get("question3_3_1");
        if(question3_3_1 != null) {
            this.question3_3_1 = (String) question3_3_1.data();
        }
        SheetData question4_1 = sheet.data().get("question4_1");
        if(question4_1 != null) {
            this.question4_1 = (Integer) question4_1.data();
        }
        SheetData question4_1_addition = sheet.data().get("question4_1_addition");
        if(question4_1_addition != null) {
            this.question4_1_addition = (String) question4_1_addition.data();
        }
        SheetData question5_1 = sheet.data().get("question5_1");
        if(question5_1 != null) {
            this.question5_1 = (Integer) question5_1.data();
        }
        SheetData question5_1_addition = sheet.data().get("question5_1_addition");
        if(question5_1_addition != null) {
            this.question5_1_addition = (String) question5_1_addition.data();
        }
        SheetData question5_1_1 = sheet.data().get("question5_1_1");
        if(question5_1_1 != null) {
            this.question5_1_1 = (Integer) question5_1_1.data();
        }
        SheetData question5_1_1_addition = sheet.data().get("question5_1_1_addition");
        if(question5_1_1_addition != null) {
            this.question5_1_1_addition = (String) question5_1_1_addition.data();
        }
        SheetData question6_1 = sheet.data().get("question6_1");
        if(question6_1 != null) {
            this.question6_1 = (Integer) question6_1.data();
        }
        SheetData question6_1_1 = sheet.data().get("question6_1_1");
        if(question6_1_1 != null) {
            this.question6_1_1 = (Integer) question6_1_1.data();
        }
        SheetData question6_1_1_addition = sheet.data().get("question6_1_1_addition");
        if(question6_1_1_addition != null) {
            this.question6_1_1_addition = (String) question6_1_1_addition.data();
        }
        SheetData question6_2_1 = sheet.data().get("question6_2_1");
        if(question6_2_1 != null) {
            this.question6_2_1 = (Integer) question6_2_1.data();
        }
        SheetData question7_1_a = sheet.data().get("question7_1_a");
        if(question7_1_a != null) {
            this.question7_1_a = (Boolean) question7_1_a.data();
        }
        SheetData question7_1_b = sheet.data().get("question7_1_b");
        if(question7_1_b != null) {
            this.question7_1_b = (Boolean) question7_1_b.data();
        }
        SheetData question7_1_c = sheet.data().get("question7_1_c");
        if(question7_1_c != null) {
            this.question7_1_c = (Boolean) question7_1_c.data();
        }
        SheetData question7_1_d = sheet.data().get("question7_1_d");
        if(question7_1_d != null) {
            this.question7_1_d = (Boolean) question7_1_d.data();
        }
        SheetData question7_1_e = sheet.data().get("question7_1_e");
        if(question7_1_e != null) {
            this.question7_1_e = (Boolean) question7_1_e.data();
        }
        SheetData question7_1_addition = sheet.data().get("question7_1_addition");
        if(question7_1_addition != null) {
            this.question7_1_addition = (String) question7_1_addition.data();
        }
        SheetData question8_1 = sheet.data().get("question8_1");
        if(question8_1 != null) {
            this.question8_1 = (Integer) question8_1.data();
        }
        SheetData question8_1_addition = sheet.data().get("question8_1_addition");
        if(question8_1_addition != null) {
            this.question8_1_addition = (String) question8_1_addition.data();
        }
        SheetData question9_1 = sheet.data().get("question9_1");
        if(question9_1 != null) {
            this.question9_1 = (Integer) question9_1.data();
        }
        SheetData question9_1_addition = sheet.data().get("question9_1_addition");
        if(question9_1_addition != null) {
            this.question9_1_addition = (String) question9_1_addition.data();
        }
        SheetData question10_1 = sheet.data().get("question10_1");
        if(question10_1 != null) {
            this.question10_1 = (Boolean) question10_1.data();
        }

        SheetData question3_2 = sheet.data().get("question3_2");
        if(question3_2 != null) {
            this.question3_2 = (Integer) question3_2.data();
        }
        SheetData question3_1_2 = sheet.data().get("question3_1_2");
        if(question3_1_2 != null) {
            this.question3_1_2 = (String) question3_1_2.data();
        }
        SheetData question4_2_a = sheet.data().get("question4_2_a");
        if(question4_2_a != null) {
            this.question4_2_a = (Boolean) question4_2_a.data();
        }
        SheetData question4_2_b = sheet.data().get("question4_2_b");
        if(question4_2_b != null) {
            this.question4_2_b = (Boolean) question4_2_b.data();
        }
        SheetData question4_2_c = sheet.data().get("question4_2_c");
        if(question4_2_c != null) {
            this.question4_2_c = (Boolean) question4_2_c.data();
        }
        SheetData question4_2_d = sheet.data().get("question4_2_d");
        if(question4_2_d != null) {
            this.question4_2_d = (Boolean) question4_2_d.data();
        }
        SheetData question4_2_e = sheet.data().get("question4_2_e");
        if(question4_2_e != null) {
            this.question4_2_e = (Boolean) question4_2_e.data();
        }
        SheetData question4_2_addition = sheet.data().get("question4_2_addition");
        if(question4_2_addition != null) {
            this.question4_2_addition = (String) question4_2_addition.data();
        }
        SheetData question5_2_a = sheet.data().get("question5_2_a");
        if(question5_2_a != null) {
            this.question5_2_a = (Boolean) question5_2_a.data();
        }
        SheetData question5_2_b = sheet.data().get("question5_2_b");
        if(question5_2_b != null) {
            this.question5_2_b = (Boolean) question5_2_b.data();
        }
        SheetData question5_2_c = sheet.data().get("question5_2_c");
        if(question5_2_c != null) {
            this.question5_2_c = (Boolean) question5_2_c.data();
        }
        SheetData question5_2_c_a = sheet.data().get("question5_2_c_a");
        if(question5_2_c_a != null) {
            this.question5_2_c_a = (Boolean) question5_2_c_a.data();
        }
        SheetData question5_2_c_b = sheet.data().get("question5_2_c_b");
        if(question5_2_c_b != null) {
            this.question5_2_c_b = (Boolean) question5_2_c_b.data();
        }
        SheetData question5_2_c_c = sheet.data().get("question5_2_c_c");
        if(question5_2_c_c != null) {
            this.question5_2_c_c = (Boolean) question5_2_c_c.data();
        }
        SheetData question5_2_c_d = sheet.data().get("question5_2_c_d");
        if(question5_2_c_d != null) {
            this.question5_2_c_d = (Boolean) question5_2_c_d.data();
        }
        SheetData question5_2_c_e = sheet.data().get("question5_2_c_e");
        if(question5_2_c_e != null) {
            this.question5_2_c_e = (Boolean) question5_2_c_e.data();
        }
        SheetData question5_2_c_f = sheet.data().get("question5_2_c_f");
        if(question5_2_c_f != null) {
            this.question5_2_c_f = (Boolean) question5_2_c_f.data();
        }
        SheetData question5_2_c_g = sheet.data().get("question5_2_c_g");
        if(question5_2_c_g != null) {
            this.question5_2_c_g = (Boolean) question5_2_c_g.data();
        }
        SheetData question5_2_c_addition = sheet.data().get("question5_2_c_addition");
        if(question5_2_c_addition != null) {
            this.question5_2_c_addition = (String) question5_2_c_addition.data();
        }
        SheetData question5_2_addition = sheet.data().get("question5_2_addition");
        if(question5_2_addition != null) {
            this.question5_2_addition = (String) question5_2_addition.data();
        }
        SheetData question6_2 = sheet.data().get("question6_2");
        if(question6_2 != null) {
            this.question6_2 = (String) question6_2.data();
        }
        SheetData question7_2_a = sheet.data().get("question7_2_a");
        if(question7_2_a != null) {
            this.question7_2_a = (Boolean) question7_2_a.data();
        }
        SheetData question7_2_b = sheet.data().get("question7_2_b");
        if(question7_2_b != null) {
            this.question7_2_b = (Boolean) question7_2_b.data();
        }
        SheetData question7_2_c = sheet.data().get("question7_2_c");
        if(question7_2_c != null) {
            this.question7_2_c = (Boolean) question7_2_c.data();
        }
        SheetData question7_2_d = sheet.data().get("question7_2_d");
        if(question7_2_d != null) {
            this.question7_2_d = (Boolean) question7_2_d.data();
        }
        SheetData question7_2_e = sheet.data().get("question7_2_e");
        if(question7_2_e != null) {
            this.question7_2_e = (Boolean) question7_2_e.data();
        }
        SheetData question7_2_addition = sheet.data().get("question7_2_addition");
        if(question7_2_addition != null) {
            this.question7_2_addition = (String) question7_2_addition.data();
        }
        SheetData question8_2 = sheet.data().get("question8_2");
        if(question8_2 != null) {
            this.question8_2 = (Integer) question8_2.data();
        }
        SheetData question8_1_2 = sheet.data().get("question8_1_2");
        if(question8_1_2 != null) {
            this.question8_1_2 = (Integer) question8_1_2.data();
        }
        SheetData question8_1_2_addition = sheet.data().get("question8_1_2_addition");
        if(question8_1_2_addition != null) {
            this.question8_1_2_addition = (String) question8_1_2_addition.data();
        }
        SheetData question8_2_2 = sheet.data().get("question8_2_2");
        if(question8_2_2 != null) {
            this.question8_2_2 = (Integer) question8_2_2.data();
        }
        SheetData question9_2_a = sheet.data().get("question9_2_a");
        if(question9_2_a != null) {
            this.question9_2_a = (Boolean) question9_2_a.data();
        }
        SheetData question9_2_b = sheet.data().get("question9_2_b");
        if(question9_2_b != null) {
            this.question9_2_b = (Boolean) question9_2_b.data();
        }
        SheetData question9_2_c = sheet.data().get("question9_2_c");
        if(question9_2_c != null) {
            this.question9_2_c = (Boolean) question9_2_c.data();
        }
        SheetData question9_2_d = sheet.data().get("question9_2_d");
        if(question9_2_d != null) {
            this.question9_2_d = (Boolean) question9_2_d.data();
        }
        SheetData question9_2_e = sheet.data().get("question9_2_e");
        if(question9_2_e != null) {
            this.question9_2_e = (Boolean) question9_2_e.data();
        }
        SheetData question9_2_addition = sheet.data().get("question9_2_addition");
        if(question9_2_addition != null) {
            this.question9_2_addition = (String) question9_2_addition.data();
        }

        SheetData questionA = sheet.data().get("questionA");
        if(questionA != null) {
            this.questionA = (String) questionA.data();
        }
        SheetData questionB = sheet.data().get("questionB");
        if(questionB != null) {
            this.questionB = (Boolean) questionB.data();
        }

        SheetData title = sheet.data().get("title");
        if(title != null) {
            this.title = (String) title.data();
        }
        SheetData firstName = sheet.data().get("firstName");
        if(firstName != null) {
            this.firstName = (String) firstName.data();
        }
        SheetData lastName = sheet.data().get("lastName");
        if(lastName != null) {
            this.lastName = (String) lastName.data();
        }
        SheetData street = sheet.data().get("street");
        if(street != null) {
            this.street = (String) street.data();
        }
        SheetData zipCode = sheet.data().get("zipCode");
        if(zipCode != null) {
            this.zipCode = (String) zipCode.data();
        }
        SheetData city = sheet.data().get("city");
        if(city != null) {
            this.city = (String) city.data();
        }
        SheetData country = sheet.data().get("country");
        if(country != null) {
            this.country = (String) country.data();
        }
        SheetData email = sheet.data().get("email");
        if(email != null) {
            this.email = (String) email.data();
        }
        SheetData phone = sheet.data().get("phone");
        if(phone != null) {
            this.phone = (String) phone.data();
        }
        SheetData fax = sheet.data().get("fax");
        if(fax != null) {
            this.fax = (String) fax.data();
        }
    }

    public Boolean getQuestion1() {
        return question1;
    }

    public Boolean getQuestion2() {
        return question2;
    }

    public Boolean getQuestion3_1() {
        return question3_1;
    }

    public String getQuestion3_1_1() {
        return question3_1_1;
    }

    public String getQuestion3_2_1() {
        return question3_2_1;
    }

    public String getQuestion3_3_1() {
        return question3_3_1;
    }

    public Integer getQuestion4_1() {
        return question4_1;
    }

    public String getQuestion4_1_addition() {
        return question4_1_addition;
    }

    public Integer getQuestion5_1() {
        return question5_1;
    }

    public String getQuestion5_1_addition() {
        return question5_1_addition;
    }

    public Integer getQuestion5_1_1() {
        return question5_1_1;
    }

    public String getQuestion5_1_1_addition() {
        return question5_1_1_addition;
    }

    public Integer getQuestion6_1() {
        return question6_1;
    }

    public Integer getQuestion6_1_1() {
        return question6_1_1;
    }

    public String getQuestion6_1_1_addition() {
        return question6_1_1_addition;
    }

    public Integer getQuestion6_2_1() {
        return question6_2_1;
    }

    public Boolean getQuestion7_1_a() {
        return question7_1_a;
    }

    public Boolean getQuestion7_1_b() {
        return question7_1_b;
    }

    public Boolean getQuestion7_1_c() {
        return question7_1_c;
    }

    public Boolean getQuestion7_1_d() {
        return question7_1_d;
    }

    public Boolean getQuestion7_1_e() {
        return question7_1_e;
    }

    public String getQuestion7_1_addition() {
        return question7_1_addition;
    }

    public Integer getQuestion8_1() {
        return question8_1;
    }

    public String getQuestion8_1_addition() {
        return question8_1_addition;
    }

    public Integer getQuestion9_1() {
        return question9_1;
    }

    public String getQuestion9_1_addition() {
        return question9_1_addition;
    }

    public Boolean getQuestion10_1() {
        return question10_1;
    }

    public Integer getQuestion3_2() {
        return question3_2;
    }

    public String getQuestion3_1_2() {
        return question3_1_2;
    }

    public Boolean getQuestion4_2_a() {
        return question4_2_a;
    }

    public Boolean getQuestion4_2_b() {
        return question4_2_b;
    }

    public Boolean getQuestion4_2_c() {
        return question4_2_c;
    }

    public Boolean getQuestion4_2_d() {
        return question4_2_d;
    }

    public Boolean getQuestion4_2_e() {
        return question4_2_e;
    }

    public String getQuestion4_2_addition() {
        return question4_2_addition;
    }

    public Boolean getQuestion5_2_a() {
        return question5_2_a;
    }

    public Boolean getQuestion5_2_b() {
        return question5_2_b;
    }

    public Boolean getQuestion5_2_c() {
        return question5_2_c;
    }

    public Boolean getQuestion5_2_c_a() {
        return question5_2_c_a;
    }

    public Boolean getQuestion5_2_c_b() {
        return question5_2_c_b;
    }

    public Boolean getQuestion5_2_c_c() {
        return question5_2_c_c;
    }

    public Boolean getQuestion5_2_c_d() {
        return question5_2_c_d;
    }

    public Boolean getQuestion5_2_c_e() {
        return question5_2_c_e;
    }

    public Boolean getQuestion5_2_c_f() {
        return question5_2_c_f;
    }

    public Boolean getQuestion5_2_c_g() {
        return question5_2_c_g;
    }

    public String getQuestion5_2_c_addition() {
        return question5_2_c_addition;
    }

    public String getQuestion5_2_addition() {
        return question5_2_addition;
    }

    public String getQuestion6_2() {
        return question6_2;
    }

    public Boolean getQuestion7_2_a() {
        return question7_2_a;
    }

    public Boolean getQuestion7_2_b() {
        return question7_2_b;
    }

    public Boolean getQuestion7_2_c() {
        return question7_2_c;
    }

    public Boolean getQuestion7_2_d() {
        return question7_2_d;
    }

    public Boolean getQuestion7_2_e() {
        return question7_2_e;
    }

    public String getQuestion7_2_addition() {
        return question7_2_addition;
    }

    public Integer getQuestion8_2() {
        return question8_2;
    }

    public Integer getQuestion8_1_2() {
        return question8_1_2;
    }

    public String getQuestion8_1_2_addition() {
        return question8_1_2_addition;
    }

    public Integer getQuestion8_2_2() {
        return question8_2_2;
    }

    public Boolean getQuestion9_2_a() {
        return question9_2_a;
    }

    public Boolean getQuestion9_2_b() {
        return question9_2_b;
    }

    public Boolean getQuestion9_2_c() {
        return question9_2_c;
    }

    public Boolean getQuestion9_2_d() {
        return question9_2_d;
    }

    public Boolean getQuestion9_2_e() {
        return question9_2_e;
    }

    public String getQuestion9_2_addition() {
        return question9_2_addition;
    }

    public String getQuestionA() {
        return questionA;
    }

    public Boolean getQuestionB() {
        return questionB;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }
}
