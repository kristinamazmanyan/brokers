package com.brokerexam.service.upload;

import java.io.InputStream;

public interface QuestionsImportService {

    boolean importExamFile(InputStream inputStream, long examId) throws Exception;
}
