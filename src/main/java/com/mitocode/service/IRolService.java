package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Rol;
import com.mitocode.model.Usuario;

public interface IRolService extends IService<Rol>{

	Integer asignar(Usuario us, List<Rol> roles);
}
