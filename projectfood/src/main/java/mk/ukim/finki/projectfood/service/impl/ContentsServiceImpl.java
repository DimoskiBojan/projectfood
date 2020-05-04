package mk.ukim.finki.projectfood.service.impl;

import mk.ukim.finki.projectfood.model.Contents;
import mk.ukim.finki.projectfood.repository.ContentsRepository;
import mk.ukim.finki.projectfood.service.ContentsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsServiceImpl implements ContentsService {
    private final ContentsRepository contentsRepository;

    public ContentsServiceImpl(ContentsRepository contentsRepository) {
        this.contentsRepository = contentsRepository;
    }

    @Override
    public List<Contents> getContentsBySourceIdAndSourceType(Integer sourceId, String sourceType) {
        return contentsRepository.findBySourceIdAndSourceType(sourceId, sourceType);
    }
}
