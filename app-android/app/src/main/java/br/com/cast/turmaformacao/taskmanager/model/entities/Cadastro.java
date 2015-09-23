package br.com.cast.turmaformacao.taskmanager.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Cadastro implements Parcelable {

    private Long id;
    private String login;
    private String password;

    public Cadastro() {
        super();
    }

    protected Cadastro(Parcel in) {
        readFromParcel(in);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cadastro cadastro = (Cadastro) o;

        if (id.equals(cadastro.id)) return false;
        if (login.equals(cadastro.login)) return false;
        return password.equals(cadastro.password);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cadastro{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id == null ? -1 : id);
        dest.writeString(login == null ? "" : login);
        dest.writeString(password == null ? "" : password);
    }

    private void readFromParcel(Parcel in) {
        id = in.readLong();
        id = id == -1 ? null : id;
        login = in.readString();
        password = in.readString();
    }


    public static final Creator<Cadastro> CREATOR = new Creator<Cadastro>() {
        public Cadastro createFromParcel(Parcel source) {
            return new Cadastro(source);
        }

        public Cadastro[] newArray(int size) {
            return new Cadastro[size];
        }
    };
}
