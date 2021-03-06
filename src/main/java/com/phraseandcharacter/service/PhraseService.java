package com.phraseandcharacter.service;

import com.phraseandcharacter.model.CharacterData;
import com.phraseandcharacter.model.PhraseData;
import com.phraseandcharacter.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhraseService {

    @Autowired
    private PhraseRepository phraseRepository;

    public synchronized PhraseData addPhrase(PhraseData phrase) {
        PhraseData phraseData = phraseRepository.findPhraseDataBy_id(phrase.get_id());
        if (phraseData != null && phraseData.get_id() != null) {
            return null;
        } else {
            PhraseData createdPhraseData = phraseRepository.save(phrase);
            return createdPhraseData;
        }
    }

    public List<PhraseData> findAll() {
        Iterable<PhraseData> it = phraseRepository.findAll();
        List<PhraseData> phrases = new ArrayList<PhraseData>();
        it.forEach(e -> phrases.add(e));
        return phrases;
    }

    public PhraseData findPhraseDataBy_id(String id) {
        return phraseRepository.findPhraseDataBy_id(id);
    }

    public List<PhraseData> findPhraseDataByPhraseContains(String phraseName) {
        Iterable<PhraseData> it = phraseRepository.findPhraseDataByPhraseContains(phraseName);
        List<PhraseData> phrases = new ArrayList<PhraseData>();
        it.forEach(e -> phrases.add(e));
        return phrases;
    }

    public void deletePhrase(String phraseId) {
        PhraseData phraseData = phraseRepository.findPhraseDataBy_id(phraseId);
        if (phraseData != null && phraseData.get_id() != null) {
            phraseRepository.delete(phraseData);
        }
    }

    public PhraseData editPhrase(PhraseData phrase) {
        PhraseData updatedPhraseData = null;
        PhraseData existingPhraseData = null;
        if (phrase != null && phrase.get_id() != null) {
            existingPhraseData = phraseRepository.findPhraseDataBy_id(phrase.get_id());
        }
        if (existingPhraseData != null && existingPhraseData.get_id() != null) {
            updatedPhraseData = phraseRepository.save(phrase);
        } else {
            return null;
        }
        return updatedPhraseData;
    }

}
