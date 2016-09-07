package com.nextinno.doshare.login.mapper;

import java.util.List;
import com.nextinno.doshare.config.db.support.DoShareDb;
import com.nextinno.doshare.domain.users.User;

/**
 * @author rsjung
 *
 */
@DoShareDb
public interface LoginMapper {
    public List<User> findByUserName();
}
