package es.eoi.java2022.recuerdamelon.dto;

import es.eoi.java2022.recuerdamelon.data.entity.User;

import java.util.List;

public class RecieversCreationDto {
        private List<String> recievers;

        // default and parameterized constructor
        public void addReciever(String name) {
            this.recievers.add(name);
        }

        // getter and setter
    public List<String> getRecievers() {
        return recievers;
    }

    public void setRecievers(List<String> recievers) {
        this.recievers = recievers;
    }
}

