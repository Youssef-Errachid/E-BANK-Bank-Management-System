public class Person {
    private int id ;
    private String name;

    Person(int id, String name) {
      this.id = id;
      this.name = name;
    }
    protected int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }
    protected String getName() {
        return name;
    }
    private void setName(String name) {
        this.name = name;
    }

}
