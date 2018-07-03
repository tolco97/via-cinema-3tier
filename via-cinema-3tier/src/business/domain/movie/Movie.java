package business.domain.movie;

import java.util.Objects;

import persistence.dto.movie.MovieDto;

public class Movie {

	private String name, genre;
	private int durationMinuites;
	private String castMembers, description;

	public Movie(String name, int durationMinuites, String genre, String castMembers, String description) {
		this.name = name;
		this.durationMinuites = durationMinuites;
		this.genre = genre;
		this.castMembers = castMembers;
		this.description = description;
	}

	public Movie(MovieDto movie) {
		this(movie.getName(), movie.getDurationMinuites(), movie.getGenre(), movie.getCastMembers(),
				movie.getDescription());
	}

	public String getName() {
		return name;
	}

	public int getDurationMinuites() {
		return durationMinuites;
	}

	public String getGenre() {
		return genre;
	}

	public String getDescription() {
		return description;
	}

	public String getCastMembers() {
		return castMembers;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Movie))
			return false;
		
		Movie other = (Movie) obj;
		
		boolean areEqual = Objects.equals(other.getName(), getName()) && other.getDurationMinuites() == getDurationMinuites() 
				&& Objects.equals(other.getGenre(), getGenre()) && Objects.equals(other.getDescription(), getDescription()) 
				&& Objects.equals(other.getCastMembers(), getCastMembers());
 		
		return areEqual;
	}
	
	@Override
	public String toString() {
		return "Movie [name= " + name + ", durationMinuites= " + durationMinuites + ", genre= " + genre + ", castMembers= "
				+ castMembers + ", description= " + description + "]";
	}
	
}
