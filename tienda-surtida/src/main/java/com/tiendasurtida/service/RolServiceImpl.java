package com.tiendasurtida.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendasurtida.entity.rol;
import com.tiendasurtida.repository.RolRepository;
import com.tiendasurtida.service.RolService;
@Service
public class RolServiceImpl implements RolService{
       private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
        public List<rol> listarRoles() {
            return rolRepository.findAll();
        }
    @Override
    public List<rol> listarRolesAsignables() {
        return rolRepository.findAll()
                .stream()
                .filter(r -> !r.getNombreRol().equalsIgnoreCase("Dueña"))
                .toList();
    }

}
