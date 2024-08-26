
public class Song implements SongInterface {
  private String title;
  private String artist;
  private String[] genres;
  private int year;
  private int bpm;
  private int energy;
  private int danceability;
  private int loudness;
  private int liveness;
  
  
  public Song(String title, String artist, String[] genres, int year, int bpm, int energy, int danceability, int loudness, int liveness) {
    this.title = title;
    this.artist = artist;
    this.genres = genres;
    this.year = year;
    this.bpm = bpm;
    this.energy = energy;
    this.danceability = danceability;
    this.loudness = loudness;
    this.liveness = liveness;
}
  
  
  
  
  @Override
  public int compareTo(SongInterface o) {
    int result = this.getLoudness() - o.getLoudness();
    
    
    return result;
  }

  @Override
  public String getTitle() {
    
    return this.title;
  }

  @Override
  public String getArtist() {
    
    return this.artist;
  }

  @Override
  public String getGenres() {
    String allGenres = "";
    for(int i = 0; i < this.genres.length; i++) {
      allGenres += this.genres[i];
    }
    
    return allGenres;
  }

  @Override
  public int getYear() {
    
    return this.year;
  }

  @Override
  public int getBPM() {
    
    return this.bpm;
  }

  @Override
  public int getEnergy() {
    
    return this.energy;
  }

  @Override
  public int getDanceability() {
    
    return this.danceability;
  }

  @Override
  public int getLoudness() {
    
    return this.loudness;
  }

  @Override
  public int getLiveness() {
    
    return this.liveness;
  }

}

