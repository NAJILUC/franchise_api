package com.nequi.franchise.franchise.services.franchises;

import com.nequi.franchise.franchise.entities.franchises.Franchise;
import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.BadRequestException;
import com.nequi.franchise.franchise.objects.utils.PaginationObj;
import com.nequi.franchise.franchise.repositories.franchises.FranchiseRepository;
import com.nequi.franchise.franchise.requests.franchises.FranchiseRequest;
import com.nequi.franchise.franchise.requests.franchises.UpdFranchiseRequest;
import com.nequi.franchise.franchise.requests.stores.StoreRequest;
import com.nequi.franchise.franchise.responses.franchises.StoreResponse;
import com.nequi.franchise.franchise.responses.franchises.TopProductStockResponse;
import com.nequi.franchise.franchise.responses.utils.BasicIdNameResponse;
import com.nequi.franchise.franchise.services.products.ProductService;
import com.nequi.franchise.franchise.services.stores.StoreService;
import com.nequi.franchise.franchise.services.utils.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con las franquicias.
 */
@Service
@RequiredArgsConstructor
public class FranchiseService {

    /**
     * Repository
     */
    private final FranchiseRepository franchiseRepository;

    /**
     * Services
     */
    private final StoreService storeService;
    private final ProductService productService;

    /**
     * Busca una franquicia por su ID.
     *
     * @param franchiseId identificador único de la franquicia
     * @return la entidad {@link Franchise} encontrada
     * @throws com.nequi.franchise.franchise.exceptions.NotFoundException si la franquicia no existe
     */
    private Franchise findByFranchiseId(Long franchiseId){
        return UtilService.checkOptionalEmpty(franchiseRepository.findById(franchiseId), ExceptionEnum.FRAN02);
    }

    /**
     * Crea una nueva franquicia en el sistema.
     *
     * @param franchiseRequest datos de la franquicia a crear
     * @return objeto con el ID y nombre de la franquicia creada
     * @throws BadRequestException si ya existe otra franquicia con el mismo nombre
     */
    @Transactional
    public ResponseEntity<BasicIdNameResponse> createFranchise(FranchiseRequest franchiseRequest) {
        Franchise franchise = new Franchise();
        this.validUniqueName(franchiseRequest.getName(), null);
        franchise.setName(franchiseRequest.getName());
        franchiseRepository.save(franchise);
        BasicIdNameResponse response = new BasicIdNameResponse(franchise);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Valida que no exista otra franquicia con el mismo nombre.
     *
     * @param name nombre de la franquicia
     * @param franchiseId id de la franquicia a excluir (para updates)
     * @throws BadRequestException si ya existe otra franquicia con ese nombre
     */
    private void validUniqueName(String name, Long franchiseId) {
        if (franchiseRepository.existsByNameAndIdNot(name, franchiseId)) {
            throw new BadRequestException(ExceptionEnum.FRAN01);
        }
    }

    /**
     * Crea una nueva sucursal dentro de una franquicia existente.
     *
     * @param franchiseId identificador de la franquicia
     * @param storeRequest datos de la sucursal a crear
     * @return representación de la sucursal creada
     */
    public ResponseEntity<StoreResponse> createStore(Long franchiseId, StoreRequest storeRequest) {
        Franchise franchise = this.findByFranchiseId(franchiseId);
        StoreResponse response = storeService.createStore(franchise, storeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Consulta los productos con mayor stock en todas las sucursales de una franquicia.
     *
     * @param franchiseId identificador de la franquicia
     * @param paginationObj objeto con los parámetros de paginación
     * @return página con los productos ordenados por stock
     */
    public ResponseEntity<Page<TopProductStockResponse>> findTopProductStock(Long franchiseId, PaginationObj paginationObj) {
        this.findByFranchiseId(franchiseId);
        return productService.findTopProductStock(franchiseId, paginationObj);
    }

    /**
     * Actualiza los datos de una franquicia existente.
     *
     * @param franchiseId identificador de la franquicia a actualizar
     * @param updFranchiseRequest datos de la franquicia actualizados
     * @return objeto con el ID y nombre de la franquicia actualizada
     * @throws BadRequestException si el nuevo nombre ya existe en otra franquicia
     */
    @Transactional
    public ResponseEntity<BasicIdNameResponse> updateFranchise(Long franchiseId, UpdFranchiseRequest updFranchiseRequest) {
        Franchise franchise = this.findByFranchiseId(franchiseId);
        this.validUniqueName(updFranchiseRequest.getName(), franchise.getId());
        if(!franchise.getName().equals(updFranchiseRequest.getName())){
            franchise.setName(updFranchiseRequest.getName());
        }
        franchiseRepository.save(franchise);
        BasicIdNameResponse response = new BasicIdNameResponse(franchise);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
