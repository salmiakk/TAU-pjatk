import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class DobreZieleBasketTests(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10)

    def dobreziele_basket_check(self):
        driver = self.driver

        driver.get("https://dobreziele.pl/")
        self.assertIn("Yerba Mate", driver.title)

        elem = driver.find_element(By.NAME, "k")
        elem.send_keys("aguamate")
        elem.send_keys(Keys.RETURN)

        wait = WebDriverWait(driver, 10)
        element = wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, 'a[href*=\'aguamate-500g\']')))
        element.click()
        
        element = wait.until(EC.element_to_be_clickable((By.CLASS_NAME, 'basket')))
        element.click()

        element = wait.until(EC.element_to_be_clickable((By.CLASS_NAME, 'basket-box')))
        element.click()

        element = wait.until(EC.element_to_be_clickable((By.XPATH, "//*[contains(text(), 'Aguamate 500g')]")))
        self.assertTrue(element)

        time.sleep(10)
    def tearDown(self):
        self.driver.close()


if __name__ == "__main__":
    unittest.main()