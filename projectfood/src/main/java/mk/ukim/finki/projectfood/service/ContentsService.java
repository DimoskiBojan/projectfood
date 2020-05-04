package mk.ukim.finki.projectfood.service;

import mk.ukim.finki.projectfood.model.Contents;

import java.util.List;

public interface ContentsService {
    // sourceType = "Nutrient" || "Compound"
    List<Contents> getContentsBySourceIdAndSourceType(Integer sourceId, String sourceType);
}
