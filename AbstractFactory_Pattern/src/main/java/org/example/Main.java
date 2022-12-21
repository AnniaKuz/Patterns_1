package org.example;

public class Main {
    public static void main(String[] args) {

        Blocknote blocknote = new Blocknote();
        NoteProvider provider = new NoteProvider();

        //PHONENUMBER
        NumberNoteFactory numberFactory = (NumberNoteFactory) provider.getNoteFactory(NoteType.valueOf("PHONENUMBER"));

        //creating a new note
        blocknote.addNote(numberFactory.create("SPAIN","695248103"));

        //testing addCountryCode()
        numberFactory.addCountryCode("FRANCE", "+33");
        blocknote.addNote(numberFactory.create("FRANCE", "600149782"));

        //trying to add number with unregistered countryCode
        blocknote.addNote(numberFactory.create("GERMANY","354068942"));

        //ADDRESS
        AddressNoteFactory addressNoteFactory = (AddressNoteFactory) provider.getNoteFactory(NoteType.valueOf("ADDRESS"));
        blocknote.addNote(addressNoteFactory.create("SPAIN, Madrid"));

        //print all to see the result
        System.out.println(blocknote);


    }
}
