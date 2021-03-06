package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MusicRepository extends JpaRepository<MusicLoad, Long> {

	List<MusicLoad> findByTitle(String title);

	List<MusicLoad> findByGenre(String genre);
	
	List<MusicLoad> findByAuthor(String author);
	
}
