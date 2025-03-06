package com.example.usobasedatos.model;

import java.util.List;

public class Categoria {
    private int id;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/*
Qué hace:
Representa un modelo de datos que corresponde al formato de la respuesta que devuelve el endpoint de la API.
Contiene:
id: el identificador único de la categoría.
name: el nombre de la categoría.
Incluye los métodos getId y setId para obtener y asignar el ID, y getName y setName para manejar el nombre.

 */