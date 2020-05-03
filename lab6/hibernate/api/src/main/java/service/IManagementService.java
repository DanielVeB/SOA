package service;

import data.Reader;

public interface IManagementService {

    Reader registerReader(Reader reader);

    Reader unregisterReader(Reader reader);
}
