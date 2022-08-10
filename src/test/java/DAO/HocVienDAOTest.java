package DAO;

import Entity.HocVien;
import Utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.Test;

@RunWith(PowerMockRunner.class)
@PrepareForTest({XJdbc.class, HocVienDAO.class})
class HocVienDAOTest {

    HocVienDAO hocVienDao;
    HocVienDAO hocVienDaoSpy;

    public HocVienDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        hocVienDao = new HocVienDAO();
        hocVienDaoSpy = PowerMockito.spy(new HocVienDAO());
    }

    @After
    public void tearDown() {
    }

    @Test(expected = Exception.class)
    public void testInsertWithNullModel() {
        HocVien model = null;
        hocVienDao.insert(model);

    }

    @Test(expected = Exception.class)
    public void testInsertWithEmptyModel() {
        HocVien model = new HocVien();

        hocVienDao.insert(model);

    }

    @Test()
    public void testInsertWithValidModel() {
        HocVien model = new HocVien();

        model.setDiem(10);
        model.setMaHV(12);
        model.setMaKH(12);
        model.setMaNH("NH12");

        hocVienDao.insert(model);

    }

    @Test(expected = Exception.class)
    public void testUpdateWithNullModel() {
        HocVien model = null;
        hocVienDao.update(model);
    }

    @Test(expected = Exception.class)
    public void testUpdateWithEmptyModel() {
        HocVien model = new HocVien();
        hocVienDao.update(model);
    }

    @Test()
    public void testUpdateWithValidModel() {
        HocVien model = new HocVien();

        model.setDiem(9);
        model.setMaHV(11);
        model.setMaKH(11);
        model.setMaNH("NH12");

        hocVienDao.update(model);
    }

    @Test(expected = Exception.class)
    public void testDeleteWithEmptyID() {
        int MaHV = 0;
        hocVienDao.delete(MaHV);
    }

    @Test(expected = Exception.class)
    public void testDeleteWithValidID() {
        int MaHV = 10;
        hocVienDao.delete(MaHV);
    }

    @Test
    public void testSelectAll() throws Exception {
        HocVien hv = new HocVien();
        List<HocVien> expResult = new ArrayList<>();
        expResult.add(hv);
        PowerMockito.doReturn(expResult)
                .when(hocVienDaoSpy, "selectBySql", ArgumentMatchers.anyString());
        List<HocVien> result = hocVienDao.seletAll();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void testSelectById() {
 
        Integer mahv = null;
        HocVienDAO instance = new HocVienDAO();
        HocVien expResult = null;
        HocVien result = instance.seletByID(mahv);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSelectBySql() {
        String sql = "";
        Object[] args = null;
        HocVienDAO instance = new HocVienDAO();
        List<HocVien> expResult = null;
        List<HocVien> result = instance.seletBySQL(sql, args);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSelectByKhoaHoc() {
        int maKH = 0;
        HocVienDAO instance = new HocVienDAO();
        List<HocVien> expResult = null;
        List<HocVien> result = instance.selectByKhoaHoc(maKH);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}
