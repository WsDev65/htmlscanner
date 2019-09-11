package com.html.scanner.service.snippet;

import java.io.IOException;
import java.util.Optional;

public interface SnippetService {
    /**
     *  Method Prepare data for snipping
     * @throws IOException
     */
    void initialize() throws IOException;

    /**
     *
     * @param id uniq identification for your original data
     */
    void setOriginalId(String id);

    /**
     *
     * @return Same data as result of snipping
     */
    Optional<String> getSimilarElement();
}
