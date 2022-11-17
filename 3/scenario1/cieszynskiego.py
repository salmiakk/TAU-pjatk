import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class TrojmiastoChelmMapTestChrome(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Chrome()

    def test_trojmiasto_chelm_map(self):
        driver = self.driver
        driver.implicitly_wait(10)

        driver.get("https://trojmiasto.pl")

        wait = WebDriverWait(driver, 10)
        element = wait.until(EC.element_to_be_clickable((By.ID, "onetrust-accept-btn-handler")))
        element.click()
        
        element = wait.until(EC.element_to_be_clickable((By.XPATH, "//*[contains(text(), 'Wiadomości')]")))
        element.click()

        element = driver.find_element(By.ID, "search_input")
        element.send_keys("Chełm")
        element.send_keys(Keys.RETURN)

        element = wait.until(EC.element_to_be_clickable((By.XPATH, "//*[contains(text(), 'Chełm - serwis dzielnicowy')]")))
        element.click()

        element = wait.until(EC.element_to_be_clickable((By.CLASS_NAME, "disctrictmapWrap__link")))
        element.click()
        
        driver.switch_to.window(driver.window_handles[1])

        element = wait.until(EC.element_to_be_clickable((By.NAME, "map_seeker_input")))
        element.send_keys("Cieszyńskiego")
        element.send_keys(Keys.RETURN)
        
        element = wait.until(EC.element_to_be_clickable((By.XPATH, "//*[contains(text(), 'ul. Władysława Cieszyńskiego')]")))
        element.click()

        driver.save_screenshot('/tmp/screenshot.jpg')

    def tearDown(self):
        self.driver.close()

class TrojmiastoChelmMapTestFirefox(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Firefox()

    def test_trojmiasto_chelm_map(self):
        driver = self.driver
        driver.implicitly_wait(10)

        driver.get("https://trojmiasto.pl")

        wait = WebDriverWait(driver, 10)
        element = wait.until(EC.element_to_be_clickable((By.ID, "onetrust-accept-btn-handler")))
        element.click()
        
        element = wait.until(EC.element_to_be_clickable((By.XPATH, "//*[contains(text(), 'Wiadomości')]")))
        element.click()

        element = driver.find_element(By.ID, "search_input")
        element.send_keys("Chełm")
        element.send_keys(Keys.RETURN)

        element = wait.until(EC.element_to_be_clickable((By.XPATH, "//*[contains(text(), 'Chełm - serwis dzielnicowy')]")))
        element.click()

        element = wait.until(EC.element_to_be_clickable((By.CLASS_NAME, "disctrictmapWrap__link")))
        element.click()
        
        driver.switch_to.window(driver.window_handles[1])

        element = wait.until(EC.element_to_be_clickable((By.NAME, "map_seeker_input")))
        element.send_keys("Cieszyńskiego")
        element.send_keys(Keys.RETURN)
        
        element = wait.until(EC.element_to_be_clickable((By.XPATH, "//*[contains(text(), 'ul. Władysława Cieszyńskiego')]")))
        element.click()

        driver.save_screenshot('/tmp/screenshot.png')

    def tearDown(self):
        self.driver.close()
if __name__ == "__main__":
    unittest.main()