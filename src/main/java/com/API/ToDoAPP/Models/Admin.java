@Entity
public class Admin extends User implements UserDetails {
    public Admin() {
        this.role = Role.ROLE_ADMIN;
    }

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }
}
