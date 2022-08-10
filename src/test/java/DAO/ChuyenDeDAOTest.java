package DAO;

import Entity.ChuyenDe;
import Utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.Test;

@RunWith(PowerMockRunner.class)
@PrepareForTest({XJdbc.class, ChuyenDeDAO.class})
class ChuyenDeDAOTest {

    ChuyenDeDAO chuyenDeDao;
    ChuyenDeDAO chuyenDeDaoSpy;

    public ChuyenDeDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        chuyenDeDao = new ChuyenDeDAO();
//        PowerMockito.mockStatic(XJdbc.class);
        chuyenDeDaoSpy = PowerMockito.spy(new ChuyenDeDAO());
    }

    @After
    public void tearDown() {
    }

    @Test(expected = Exception.class)
    public void testInsertWithNullModel() {
        ChuyenDe model = null;

        chuyenDeDao.insert(model);
    }

    @Test(expected = NoSuchMethodError.class)
    public void testInsertWithEmptyModel() {
        ChuyenDe model = new ChuyenDe();
        chuyenDeDao.insert(model);
    }

    @Test
    public void testInsertWithValidModel() {
        ChuyenDe model = new ChuyenDe();
        model.setHinh("test.png");
        model.setHocPhi(100);
        model.setMaCD("CD02");
        model.setMoTa("test");
        model.setTenCD("CD Test");
        model.setThoiLuong(20);

        chuyenDeDao.insert(model);
    }

    @Test(expected = Exception.class)
    public void testUpdateWithNullModel() {
        ChuyenDe model = null;
        chuyenDeDao.update(model);
    }

    @Test(expected = Exception.class)
    public void testUpdateWithEmptyModel() {
        ChuyenDe model = new ChuyenDe();
        chuyenDeDao.update(model);
    }

    @Test()
    public void testUpdateWithValidModel() {
        ChuyenDe model = new ChuyenDe();
        model.setHinh("test.png");
        model.setHocPhi(100);
        model.setMaCD("CD01");
        model.setMoTa("test");
        model.setTenCD("CD Test");
        model.setThoiLuong(20);
        chuyenDeDao.update(model);
    }

    @Test(expected = Exception.class)
    public void testDeleteWithEmptyID() {
        String MaCD = "";
        chuyenDeDao.delete(MaCD);
    }

    @Test(expected = Exception.class)
    public void testDeleteWithNullID() {
        String MaCD = null;
        chuyenDeDao.delete(MaCD);
    }

    @Test()
    public void testDeleteWithValidID() {
        String MaCD = "CD01";
        chuyenDeDao.delete(MaCD);
    }

    @Test
    public void testSelectAll() throws Exception {
        ChuyenDe chuyenDe = new ChuyenDe();
        List<ChuyenDe> expResult = new ArrayList<>();
        expResult.add(chuyenDe);
        PowerMockito.doReturn(expResult)
                .when(chuyenDeDaoSpy, "selectBySql", ArgumentMatchers.anyString());
        List<ChuyenDe> result = chuyenDeDao.seletAll();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void testSelectByIdWithNotFound() throws Exception {
        String macd = "";

        ChuyenDe expResult = null;
        List<ChuyenDe> resultList = new ArrayList<>();
        PowerMockito.doReturn(resultList)
                .when(chuyenDeDaoSpy, "selectBySql", ArgumentMatchers.anyString(),
                         ArgumentMatchers.any());

        ChuyenDe result = chuyenDeDaoSpy.seletByID(macd);

        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void testSelectByIdWithFound() throws Exception {
        String macd = "12";

        ChuyenDe expResult = new ChuyenDe();
        List<ChuyenDe> resultList = new ArrayList<>();
        resultList.add(expResult);

        PowerMockito.doReturn(resultList)
                .when(chuyenDeDaoSpy, "selectBySql", ArgumentMatchers.anyString(),
                         ArgumentMatchers.any());

        ChuyenDe result = chuyenDeDaoSpy.seletByID(macd);

        assertThat(result, CoreMatchers.is(expResult));
    }

//    @Test
//    public void testSelectBySql() {
//        String sql = "";
//        Object[] args = null;
//        ChuyenDeDao instance = new ChuyenDeDao();
//        List<ChuyenDe> expResult = null;
//        List<ChuyenDe> result = instance.selectBySql(sql, args);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
}
