package Esprit.Entities;

public class Review {

    private Integer id;

    private Integer gameId;

    private Integer score;

    private String avis;

    // Complete constructor
    public Review(Integer id, Integer gameId, Integer score, String avis){
        this.id = id;
        this.gameId = gameId;
        this.score = score;
        this.avis = avis;
    }

    // Constructor without ID
    public Review( Integer gameId, Integer score, String avis){
        this.gameId = gameId;
        this.score = score;
        this.avis = avis;
    }

    // Deconstructor
    public Review(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }
}
