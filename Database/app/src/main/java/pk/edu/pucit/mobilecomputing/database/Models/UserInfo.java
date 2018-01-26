package pk.edu.pucit.mobilecomputing.database.Models;

/**
 * Created by abc on 1/20/18.
 *
 * @package pk.edu.pucit.mobilecomputing.database.Models
 * @project Database
 */

public class UserInfo {
    private String name;
    private String email;
    private String address;
    private int id;

    public UserInfo(int id, String name, String email, String address) {
        setId(id);
        setName(name);
        setEmail(email);
        setAddress(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
