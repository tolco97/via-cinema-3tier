package persistence.dto.movie;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import business.domain.movie.Movie;

@Entity
@Table(name = "movies")
@DynamicUpdate(value = true)
public class MovieDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "duration_minuites")
	private int durationMinuites;

	@Column(name = "genre")
	private String genre;

	@Column(name = "cast_members")
	private String castMembers;

	@Column(name = "description")
	private String description;

	public MovieDto() {}
	
	// Used in DAO
	public MovieDto(String name, int durationMinuites, String genre, String description, String castMembers) {
		setName(name);
		setDurationMinuites(durationMinuites);
		setGenre(genre);
		setCastMembers(castMembers);
		setDescription(description);
	}

	// Used in the business logic
	public MovieDto(Movie movie) {
		this(movie.getName(), movie.getDurationMinuites(), movie.getGenre(), movie.getDescription(), movie.getCastMembers());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDurationMinuites() {
		return durationMinuites;
	}

	public void setDurationMinuites(int durationMinuites) {
		this.durationMinuites = durationMinuites;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCastMembers() {
		return castMembers;
	}

	public void setCastMembers(String castMembers) {
		this.castMembers = castMembers;
	}
	
	@Override
	public String toString() {
		return "MovieDto [name=" + name + ", durationMinuites=" + durationMinuites + ", genre=" + genre
				+ ", castMembers=" + castMembers + ", description=" + description + "]";
	}

}
