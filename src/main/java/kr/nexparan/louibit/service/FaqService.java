package kr.nexparan.louibit.service;

import kr.nexparan.louibit.model.Faq;
import kr.nexparan.louibit.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaqService {
    @Autowired
    private FaqRepository faqRepository;


    public Faq save(Faq faq) {
        faq.setContent(faq.getContent().replace("\r\n", "<br />"));
        return faqRepository.save(faq);
    }
}
