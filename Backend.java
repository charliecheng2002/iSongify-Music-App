import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Backend implements BackendInterface {
  
  private IterableRedBlackTree<SongInterface> songs;
  private List<SongInterface> songList;
  private List<SongInterface> filteredSongs;
  private String filteredGenre;
  
  // indicator that shows whether getRange() was called
  private boolean getRangeCalled = false;
  
  public Backend(IterableRedBlackTree<SongInterface> tree) {
    this.songs = tree;
    this.songList = new ArrayList<>();
    filteredSongs = new ArrayList<>();
    filteredGenre = null;
  }
  

  @Override
  public void readData(String filename) throws IOException {
    try {
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      scanner.nextLine();
      while(scanner.hasNextLine()) {
        
        String line = scanner.nextLine();
        String line2 = line;
        
        String[] parts = new String[14];
        
        int numQuotes = 0;
        int lastQuoteIndex = 0;
        
        for(int k = 0; k < line.length(); k++) {
          if(line.charAt(k) == '\"') {
            numQuotes++;
            lastQuoteIndex = k;
          }
        }
        
        
        if(numQuotes > 0) {
          String title = line.substring(1,lastQuoteIndex);
          line2 = line2.replace(line2.substring(0,lastQuoteIndex+2), "");
          
          String[] remainingParts = line2.split(",");
          
          String artist = remainingParts[0];
          String[] genres = remainingParts[1].split(";");
          int year = Integer.parseInt(remainingParts[2]);
          int bpm = Integer.parseInt(remainingParts[3]);
          int energy = Integer.parseInt(remainingParts[4]);
          int danceability = Integer.parseInt(remainingParts[5]);
          int loudness = Integer.parseInt(remainingParts[6]);
          int liveness = Integer.parseInt(remainingParts[7]);
          
          Song song = new Song(title, artist, genres, year, bpm, energy, danceability, loudness, liveness);
          songList.add(song);
          songs.insert(song);
          
//          Iterator<SongInterface> iter = songs.iterator();
//          while (iter.hasNext()) {
//            
//            System.out.println("->" + iter.next().getTitle());
//          }
//          System.out.println(songs.size);
//          System.out.println(songs.toInOrderString());
        }
        else {
          parts = line.split(",");
          String title = parts[0];
          String artist = parts[1];
          String[] genres = parts[2].split(";");
          int year = Integer.parseInt(parts[3]);
          int bpm = Integer.parseInt(parts[4]);
          int energy = Integer.parseInt(parts[5]);
          int danceability = Integer.parseInt(parts[6]);
          int loudness = Integer.parseInt(parts[7]);
          int liveness = Integer.parseInt(parts[8]);
          
          Song song = new Song(title, artist, genres, year, bpm, energy, danceability, loudness, liveness);
          songList.add(song);
          songs.insert(song);
          
//          Iterator<SongInterface> iter = songs.iterator();
//          while (iter.hasNext()) {
//            
//            System.out.println("->" + iter.next().getTitle());
//          }
//          System.out.println(songs.size);
//          System.out.println(songs.toInOrderString());
        }
        
      }
    } catch (IOException e){
      System.out.println("Can't read.");
    }
    
    
  }

  @Override
  public List<String> getRange(int low, int high) {
    getRangeCalled = true;
    
    
    List<String> titles = new ArrayList<>();
    filteredSongs.clear();
    
    Iterator<SongInterface> it = songs.iterator();
    
    while(it.hasNext()) {
      SongInterface song = it.next();
    }
    
    for(SongInterface song: songs) {
      if(song.getLoudness() >= low && song.getLoudness() <= high) {
        if(filteredGenre == null || song.getGenres().contains(filteredGenre)) {
          filteredSongs.add(song);
          titles.add(song.getTitle());
        }
      }
    }
    
    
    Collections.sort(filteredSongs, Comparator.comparingInt(SongInterface::getLoudness));
    
    return titles;
    
    
    
  }
  
  

  @Override
  public List<String> filterByGenre(String genre) {
    filteredGenre = genre;
    List<String> titles = new ArrayList<>();
    if(!filteredSongs.isEmpty()) {
      for(SongInterface song: filteredSongs) {
        if(song.getGenres().contains(genre)) {
          titles.add(song.getTitle());
        }
      }
    }
    
    return titles;
  }

  @Override
  public List<String> fiveMostLive() {
    if(getRangeCalled == false) {
      throw new IllegalStateException("GetRange() was not previously called.");
    }
    
    List<SongInterface> currFiltered = new ArrayList<>(filteredSongs);
    
    
    
    currFiltered.sort(Comparator.comparingInt(SongInterface::getLiveness).reversed());
    
    List<String> mostLiveTitles = new ArrayList<>();
    
    int count = 0;
    
    for(SongInterface song: currFiltered) {
      if(count >= 5) {
        break;
      }
      mostLiveTitles.add(song.getTitle());
      count++;
    }
    
    
    return mostLiveTitles;
  }
  
  @Override
  public int getSongsSize() {
    return songs.size();
  }
  
  @Override
  public int getFilteredSongsSize() {
    return filteredSongs.size();
  }
  
  @Override
  public List<String> getAllSongs(){
    List<String> result = new ArrayList<>();
    for(SongInterface song: songs) {
      result.add(song.getTitle());
    }
    
    
    return result;
  }
  
  @Override
  public List<String> getAllFilteredSongs(){
    List<String> result = new ArrayList<>();
    for(int i = 0; i < filteredSongs.size(); i++) {
      result.add(filteredSongs.get(i).getTitle());
    }
    return result;
    
//    return null;
  }
  
}
