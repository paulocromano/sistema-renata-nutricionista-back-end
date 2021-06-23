package br.com.renatanutricionista.ficha.identificacao.historico.social.resource;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.ImagemColoracaoDiureseDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.coloracao.ColoracaoDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.service.ImagemColoracaoDiureseService;


@RestController
@RequestMapping("/imagem-cor-diurese")
public class ImagemColoracaoDiureseResource {

	@Autowired
	private ImagemColoracaoDiureseService imagemColoracaoDiureseService;
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@GetMapping
	public ResponseEntity<List<ImagemColoracaoDiureseDTO>> buscarImagensCoresDiurese() {
		return imagemColoracaoDiureseService.buscarImagensCoresDiurese();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@PostMapping("/upload")
	public ResponseEntity<Void> uploadImagemProduto(@RequestParam MultipartFile imagem) {
		return imagemColoracaoDiureseService.uploadImagemCorDiurese(ColoracaoDiurese.AMARELO_ESCURO, imagem);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirImagem(@PathVariable Integer id) {
		return imagemColoracaoDiureseService.excluirImagem(id);
	}
}
