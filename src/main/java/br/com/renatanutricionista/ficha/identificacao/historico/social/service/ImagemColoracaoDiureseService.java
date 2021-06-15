package br.com.renatanutricionista.ficha.identificacao.historico.social.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.renatanutricionista.exception.custom.FileException;
import br.com.renatanutricionista.exception.custom.IntegrityConstraintViolationException;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.ImagemColoracaoDiureseDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.coloracao.ColoracaoDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.ImagemColoracaoDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.repository.ImagemColoracaoDiureseRepository;


@Service
public class ImagemColoracaoDiureseService {

	@Autowired
	private ImagemColoracaoDiureseRepository imagemColoracaoDiureseRepository;
	
	
	public ResponseEntity<List<ImagemColoracaoDiureseDTO>> buscarImagensCoresDiurese() {
		List<ImagemColoracaoDiurese> imagensCoresDiurese = imagemColoracaoDiureseRepository.findAll();
		
		return ResponseEntity.ok().body(ImagemColoracaoDiureseDTO.converterParaListaImagemCorDiureseDTO(imagensCoresDiurese));
	}

	
	public ResponseEntity<Void> uploadImagemCorDiurese(ColoracaoDiurese coloracaoDiurese, MultipartFile imagem) {
		verificarSeColoracaoJaExiste(coloracaoDiurese);
		verificarSeImagemEValida(imagem);
		
		try {
			byte[] bytesImagem = imagem.getBytes();
			String base64Imagem = Base64.getEncoder().encodeToString(bytesImagem);
			
			ImagemColoracaoDiurese imagemColoracaoDiurese = new ImagemColoracaoDiurese(coloracaoDiurese, base64Imagem);
			imagemColoracaoDiureseRepository.save(imagemColoracaoDiurese);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private void verificarSeImagemEValida(MultipartFile imagem) {
		String extensao = imagem.getOriginalFilename().split("\\.")[1];
		
		if (!extensao.equals("png")) {
			throw new FileException("Somente imagens PNG e JPG são permitidas!");
		}
		
		if (imagem.getSize() > 500000L) {
			throw new FileException("O tamanho da Imagem deve ter no máximo 0.5MB!");
		}
		
		verificarTamanhoImagem(imagem);
	}
	
	
	private void verificarTamanhoImagem(MultipartFile imagem) {
		try {
			BufferedImage bufferedImage = ImageIO.read(imagem.getInputStream());
			
			if (bufferedImage.getHeight() > 300 || bufferedImage.getWidth() > 300) {
				throw new FileException("A resolução da Imagem deve ser no máximo 300 x 300px!");
			}
		} 
		catch (IOException e) {
			throw new FileException("Erro ao ler a imagem!");
		}
	}
	
	
	public ResponseEntity<Void> excluirImagem(Integer id) {
		verificarSeImagemCorDiureseExiste(id);
		imagemColoracaoDiureseRepository.deleteById(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	private void verificarSeColoracaoJaExiste(ColoracaoDiurese coloracaoDiurese) {
		Optional<ImagemColoracaoDiurese> imagemColoracaoDiurese = imagemColoracaoDiureseRepository.findByColoracaoDiurese(coloracaoDiurese);
		
		if (imagemColoracaoDiurese.isPresent())
			throw new IntegrityConstraintViolationException("Coloração da diurese já existe!");
	}
	
	
	private ImagemColoracaoDiurese verificarSeImagemCorDiureseExiste(Integer id) {
		Optional<ImagemColoracaoDiurese> imagemColoracaoDiurese = imagemColoracaoDiureseRepository.findById(id);
		
		if (imagemColoracaoDiurese.isEmpty())
			throw new ObjectNotFoundException("Imagem da cor da diurese não encontrada!");
		
		return imagemColoracaoDiurese.get();
	}
}
