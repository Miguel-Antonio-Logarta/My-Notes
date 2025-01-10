public class Main {
    public static void main(String[] args) {
        Person singerPerson = new Singer();
        // Access data fields of subclass object
        System.out.println(singerPerson.getName()); // Person
        // System.out.println(singerPerson.getSong()); // ERROR: not found

        // Access methods of subclass object
        singerPerson.sayHelloPerson(); // Hello. I am a Person.
        // singerPerson.sayHelloSinger(); // ERROR: not found

        Student studentSub = new Student();
        Person studentSuper = (Person) studentSub;
        studentSub = (Student) studentSuper;
        System.out.println((studentSuper == studentSub) ? "Same object" : "Different objects");
    }


}