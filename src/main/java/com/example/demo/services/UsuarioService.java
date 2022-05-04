package com.example.demo.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<Usuario> getById(Long id) {
        ArrayList<Usuario> lista = new ArrayList<>();
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        lista.add(usuario.get());
        return lista;
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        String passwordMD5 = this.encriptarPassword(usuario.getPassword());
        usuario.setPassword(passwordMD5);
        return usuarioRepository.save(usuario);
    }

    public Usuario editar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean deleteUsuarioById(Long id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    public String encriptarPassword(String source) {
        String md5 = null;
        try {
            MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
            mdEnc.update(source.getBytes(), 0, source.length());
            md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
        } catch (Exception ex) {
            return null;
        }
        return md5;
    }

    public boolean deleteUsuario(Long id) {
        try {
            this.deleteUsuarioById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
