import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class PepperAppleFirstHotDeal(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Chrome()

    def test_pepper_search(self):
        driver = self.driver
        driver.implicitly_wait(10)

        driver.get("https://pepper.pl")
        self.assertIn("Pepper.pl", driver.title)

        elem = driver.find_element(By.NAME, "q")
        elem.send_keys("apple")
        elem.send_keys(Keys.RETURN)
        
        time.sleep(10)
    def tearDown(self):
        self.driver.close()


if __name__ == "__main__":
    unittest.main()