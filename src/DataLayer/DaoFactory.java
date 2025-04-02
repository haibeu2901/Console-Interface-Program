/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import DataLayer.ProductDao.IProductDao;
import DataLayer.ProductDao.ProductDao;

/**
 *
 * @author beu29
 */
public class DaoFactory implements IDaoFactory {

    IFileManager fileManager;

    public DaoFactory() {
    }

    public DaoFactory(String inputDataFile) {
        this.fileManager = new FileManager(inputDataFile);
    }

    @Override
    public IProductDao productDao() throws Exception {
        return new ProductDao(fileManager);
    }

}
