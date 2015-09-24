package br.com.cast.turmaformacao.taskmanager.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Task implements Parcelable {

    private Long id;

    @JsonProperty("_id")
    private Long web_id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    private Label label;

    public Task() {
        super();
    }

    public Task(Parcel imp) {
        super();
        readFromParcel(imp);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeb_id() {
        return web_id;
    }

    public void setWeb_id(Long web_id) {
        this.web_id = web_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!id.equals(task.id)) return false;
        if (!web_id.equals(task.web_id)) return false;
        if (!name.equals(task.name)) return false;
        return description.equals(task.description);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (web_id != null ? web_id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", web_id=" + web_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", label=" + label +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id == null ? -1 : id);
        dest.writeLong(web_id == null ? -1 : web_id);
        dest.writeString(name == null ? "" : name);
        dest.writeString(description == null ? "" : description);
    }

    public void readFromParcel(Parcel imp) {

        id = imp.readLong();
        id = id == -1 ? null : id;
        web_id = imp.readLong();
        web_id = web_id == -1 ? null : web_id;
        name = imp.readString();
        description = imp.readString();

    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {

        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }

    };

}
