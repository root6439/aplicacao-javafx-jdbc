package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("Botão funcionando 1");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	
	private synchronized void loadView(String absoluteName) {
		try {
			
			//abrir recurso do arquivo enviado como parametro
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			
			VBox newVBox = loader.load();
			
			//pegar referencia da cena 
			Scene mainScene = Main.getMainScene();
			
			//operação pra pegar o conteudo da tela
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			//pegar primeiro filho do vbox da janela principal
			Node mainMenu = mainVBox.getChildren().get(0);
			
			//limoar todos os filhos do main vbox
			mainVBox.getChildren().clear();
			
			//adicionar o main menu
			mainVBox.getChildren().add(mainMenu);
			
			//adicionar coleção, no caso os filhos da janela que se quer abrir
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
		} catch (IOException e) {
			Alerts.showAlert("Input/Output exception", "Erro carregando a página", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private synchronized void loadView2(String absoluteName) {
		try {
			
			//abrir recurso do arquivo enviado como parametro
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			
			VBox newVBox = loader.load();
			
			//pegar referencia da cena 
			Scene mainScene = Main.getMainScene();
			
			//operação pra pegar o conteudo da tela
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			//pegar primeiro filho do vbox da janela principal
			Node mainMenu = mainVBox.getChildren().get(0);
			
			//limoar todos os filhos do main vbox
			mainVBox.getChildren().clear();
			
			//adicionar o main menu
			mainVBox.getChildren().add(mainMenu);
			
			//adicionar coleção, no caso os filhos da janela que se quer abrir
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			DepartmentListController controller = loader.getController();
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
			
		} catch (IOException e) {
			Alerts.showAlert("Input/Output exception", "Erro carregando a página", e.getMessage(), AlertType.ERROR);
		}
	}

}
