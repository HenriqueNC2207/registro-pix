package dev.akif.library.pix;
import dev.akif.crud.CRUDController;
import dev.akif.crud.common.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/pix")
@RestController
@Tag(name = "pix", description = "CRUD operations for pix")
public class PixController extends CRUDController<
        UUID,
        PixEntity,
        Pix,
        PixDTO,
        CreatePix,
        UpdatePix,
        CreatePixDTO,
        UpdatePixDTO,
        PixMapper,
        PixDTOMapper,
        PixRepository,
        PixService> {

    private final PixService pixService;
    private final PixMapper pixMapper;
     private final PixDTOMapper pixDTOMapper;
    public PixController(final PixService service, final PixDTOMapper pixDTOMapper, final PixMapper pixMapper) {
        super("Pix", service, pixDTOMapper);
        this.pixService = service;
        this.pixMapper = pixMapper;
        this.pixDTOMapper = pixDTOMapper;
    }
    private static final String LIST_BOOKS_SUMMARY = "List books of author";
    private static final String LIST_BOOKS_DESCRIPTION = "End point para crição de chave pix";
    private static final String LIST_BOOKS_RESPONSE = "Chave pix criada.";


    @ApiResponse(responseCode = CODE_OK, description = LIST_BOOKS_RESPONSE)
    @ApiResponse(responseCode = "422", description = "Inclusão não realizada, não respeita condições")
    @PostMapping(path = "/CriarPix", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = LIST_BOOKS_SUMMARY, description = LIST_BOOKS_DESCRIPTION)
    @ResponseBody
    public ResponseEntity<String> createUsingRepository(
            @Valid @RequestBody CreatePixDTO createPixDTO, // Adicione esta linha
            @Parameter(hidden = true)
            @PathVariable
            final Map<String, String> pathVariables,
            @Parameter(hidden = true)
            HttpServletRequest request  
    ) { 
        final var parameters = new Parameters(pathVariables, request);
        Instant now = Instant.now();
        CreatePix createPix = pixDTOMapper.createDTOToCreateModel(createPixDTO, parameters);
        PixEntity pixEntity = pixMapper.entityToBeCreatedFrom(createPix, now);
        Object createdPix = pixService.createPix(pixEntity, parameters);
        if (createdPix instanceof PixEntity) {
            PixEntity createdEntity = (PixEntity) createdPix;
            if (createdEntity.getId() == null) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Chave criada não respeitou as condições necessárias." + createdPix.toString());
            }
            return ResponseEntity.ok("Chave Pix criado com sucesso. Id da chave é :" + createdEntity.getId().toString());
        
        } else if (createdPix instanceof String) {
            // Handle validation message
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body((String) createdPix);
        } else {
            // Handle unexpected type
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected response type");
        } 
    }

    @ApiResponse(responseCode = CODE_OK, description = LIST_BOOKS_RESPONSE)
    @ApiResponse(responseCode = "422", description = "ALteração não realizada, não respeita condições")
    @PutMapping(path = "/AlterarPix/{id}", produces = MediaType.APPLICATION_JSON_VALUE) // Adicione o {idChavePix} ao caminho
    @Operation(summary = "Altera dados de uma chave pix", description = "End point para alteração da chave pix")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> updateUsingRepository(
            @Valid @RequestBody UpdatePixDTO updatePixDTO,
            @Parameter(hidden = true)
            @PathVariable
            final Map<String, String> pathVariables,
            @Parameter(name = "id", in = ParameterIn.PATH, description = "Id Chave Pix")
            @PathVariable("id")
            final UUID pixUuid,
            @Parameter(hidden = true)
            HttpServletRequest request
    ) { 
        final var parameters = new Parameters(pathVariables, request);
        Instant now = Instant.now();
        UpdatePix updatePix = pixDTOMapper.updateDTOToUpdateModel(updatePixDTO, parameters);
        PixEntity pixEntity = pixMapper.entityToBeUpdateEntity(updatePix, now);
        Object updatedPix = pixService.updatePix(pixEntity, parameters, pixUuid);
        if (updatedPix instanceof PixEntity) {
            PixEntity updatedEntity = (PixEntity) updatedPix;
            if (updatedEntity.getId() == null) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Alterações cedidas não respeitaram as condições necessárias." + updatedPix.toString());
            }
            return ResponseEntity.ok("Chave Pix criado com sucesso. Id da chave é :" + updatedEntity.getId().toString());
        
        } else if (updatedPix instanceof String) {
            // Handle validation message
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body((String) updatedPix);
        } else {
            // Handle unexpected type
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected response type");
        } 
    }
}
