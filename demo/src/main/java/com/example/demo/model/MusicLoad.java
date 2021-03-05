package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table (name = "music")

public class MusicLoad {
	
	@Override
	public String toString() {
		return "Entity [id=" + id + ", music=" + music + ", title=" + title + ", author=" + author + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "music")
	private String music;

	@Column(name = "title")
	private String title;
	
	@Column(name = "author")
	private String author;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


}
