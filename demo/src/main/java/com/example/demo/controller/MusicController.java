package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MusicLoad;
import com.example.demo.model.MusicRepository;


@CrossOrigin(origins = "http://localhost:8081") // used for vue
@RestController
@RequestMapping("/api")
public class MusicController {

	@Autowired
	MusicRepository musicRepository;

	@GetMapping("/music")
	public ResponseEntity<List<MusicLoad>> getAllMusic(@RequestParam(required = false)
	String title) {
		try {
			List<MusicLoad> musics = new ArrayList<MusicLoad>();

			if (title == null)
				musicRepository.findAll().forEach(musics::add);
			else
				musicRepository.findByTitle(title).forEach(musics::add);

			if (musics.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(musics, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/music/{id}")
	public ResponseEntity<MusicLoad> getMusicById(@PathVariable("id") long id) {
		Optional<MusicLoad> musicData = musicRepository.findById(id);

		if (musicData.isPresent()) {
			return new ResponseEntity<>(musicData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/music")
	public ResponseEntity<MusicLoad> createTutorial(@RequestBody MusicLoad music) {
		try {
			MusicLoad _data = musicRepository.save(new MusicLoad(music.getTitle(), music.getAuthor(), music.getGenre()));
			return new ResponseEntity<>(_data, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PutMapping("/music/{id}")
	public ResponseEntity<MusicLoad> updateMusic(@PathVariable("id") long id, @RequestBody MusicLoad music) {
		Optional<MusicLoad> musicData = musicRepository.findById(id);

		if (musicData.isPresent()) {
			MusicLoad _data = musicData.get();
			_data.setAuthor(music.getAuthor());
			_data.setTitle(music.getTitle());
			_data.setGenre(music.getGenre());
			return new ResponseEntity<>(musicRepository.save(_data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/music/{id}")
	public ResponseEntity<HttpStatus> deleteMusic(@PathVariable("id") long id) {
		try {
			musicRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/courses")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			musicRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}

