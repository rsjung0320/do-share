package com.nextinno.doshare.tags;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rsjung
 *
 */
public interface TagRepository extends JpaRepository<Tag, Long>{

    /**
     * @param tag
     * @return
     */
    Tag findByName(String name);

}
