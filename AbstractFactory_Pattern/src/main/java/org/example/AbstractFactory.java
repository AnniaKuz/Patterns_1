package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
class Blocknote {
    List<Note> notes;

    {
        notes = new ArrayList<>();
    }

    public void addNote(Note note) {
        if (note != null) {
            notes.add(note);
        } else {
            System.out.println("This note can't be added");
            ;
        }
    }
}
interface Note{
    public String getNoteContent();

}
@AllArgsConstructor
@Data
@ToString
class AddressNote implements Note{
    private String address;
    @Override
    public String getNoteContent(){
        return this.address;
    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
class NumberNote implements Note{
    private String code;
    private String number;

@Override
    public String getNoteContent(){
        return code+" "+ number;
    }
}


enum NoteType{
    ADDRESS, PHONENUMBER;
}

interface  NoteAbstractFactory{
    public Note create(String ... noteContent);

}

class AddressNoteFactory implements NoteAbstractFactory {
    @Override
    public Note create(String ... noteContent){
        return (Note) new AddressNote(noteContent[0]);
    }
}
class NumberNoteFactory implements NoteAbstractFactory {
    static Map<String, String> codeMap = new HashMap<String, String>() {{
        put("SPAIN", "+34");
        put("ITALY", "+39");
    }};

    public void addCountryCode(String country, String code) {
        codeMap.put(country, code);
    }


    @Override
    public Note create(String... noteContent) throws IllegalArgumentException{
        if (true) {
            String countryCode = noteContent[0];
            String number = noteContent[1];

            if (codeMap.containsKey(countryCode)) {
                NumberNote num = new NumberNote();
                num.setNumber(number);
                num.setCode(codeMap.get(countryCode));
                return (Note) num;
            }
        } else {
            throw new IllegalArgumentException("This country code does not exist");
        }
        return null;
    }
}

class NoteProvider{
    public NoteAbstractFactory getNoteFactory(NoteType noteType){
        try {
            switch (noteType) {
                case ADDRESS:
                    return new AddressNoteFactory();

                case PHONENUMBER:
                    return new NumberNoteFactory();
            }
        }catch (IllegalArgumentException e){
                System.out.println("This note type does not exist");
            }
        return null;
    }
}


