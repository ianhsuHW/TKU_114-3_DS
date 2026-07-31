public class Employee {
    private int id;
    private String name;
    private String department;
    private String extension;

    public Employee(
        int id,
        String name,
        String department,
        String extension
    ) {
        this.id = id;

        if (name == null) {
            this.name = "";
        } else {
            this.name = name.trim();
        }

        if (department == null) {
            this.department = "";
        } else {
            this.department = department.trim();
        }

        if (extension == null) {
            this.extension = "";
        } else {
            this.extension = extension.trim();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "編號 " + id
            + "｜姓名 " + name
            + "｜部門 " + department
            + "｜分機 " + extension;
    }
}
