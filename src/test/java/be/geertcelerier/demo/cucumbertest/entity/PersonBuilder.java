package be.geertcelerier.demo.cucumbertest.entity;

public class PersonBuilder {

    private Person person;

    public PersonBuilder() {
        this.person = new Person();
    }

    public PersonBuilder id(Integer id) {
        person.setId(id);
        return this;
    }

    public PersonBuilder firstName(String firstName) {
        person.setFirstName(firstName);
        return this;
    }

    public PersonBuilder lastName(String lastName) {
        person.setLastName(lastName);
        return this;
    }

    public Person build() {
        return person;
    }
}
