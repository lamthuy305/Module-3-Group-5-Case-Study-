package com.codegym.service.role;

import com.codegym.dao.role.IRoleDao;
import com.codegym.dao.stone.IStoneDao;
import com.codegym.model.Role;

import java.util.List;

public class RoleService implements IRoleService {
    private IRoleDao roleDao;

    public RoleService(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public boolean create(Role role) {
        return false;
    }

    @Override
    public boolean updateById(int id, Role role) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
