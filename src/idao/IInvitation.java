package idao;

public interface IInvitation {
    // publish a new invitation;
    String publishInvitation(String author, String head, String content);
    // query all invitations published;
    String queryAllInvitations();
    // query those invitations that author favourite;
    String queryFavouriteInvitations(String author);
    // query those invitations published by author;
    String queryOwnInvitations(String author);
    // collect a invitation;
    String collectInvitation(String author, String owner, String date, String content, String head);
    // discollect the invitation;
    String discollectInvitation(String author, String owner, String date, String content);
    // delete invitation user published;
    String deleteOwnInvitation(String author, String content, String date);
    // is the invitation collected?
    String isCollected(String author, String content, String date);
}
