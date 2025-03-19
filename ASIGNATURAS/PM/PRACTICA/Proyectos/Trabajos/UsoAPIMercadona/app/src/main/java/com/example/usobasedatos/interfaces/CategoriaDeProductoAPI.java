package com.example.usobasedatos.interfaces;

import com.example.usobasedatos.model.Categoria;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoriaDeProductoAPI {
    @GET("categories/{id}")
    public Call<Categoria> find(@Path("id") int id);
}

/*
Este código define una arquitectura en la que la aplicación interactúa
con una API REST para obtener información sobre una categoría de productos.

Define cómo realizar una solicitud GET a la API.
Usa la anotación @GET("categories/{id}") para especificar el endpoint de la API.
La parte categories/{id} indica que el endpoint necesita un parámetro dinámico llamado id.
El método find toma un entero id como argumento y lo inserta en el {id} del endpoint gracias a la anotación @Path.
Retorna un objeto Call<Categoria>, que representa la llamada que se ejecutará para obtener los datos de la categoría desde el servidor.

 */