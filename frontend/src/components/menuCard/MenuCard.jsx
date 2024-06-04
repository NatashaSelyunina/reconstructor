import React, { useState } from "react";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import styles from "./MenuCard.module.css";
import { DragDropContext, Droppable, Draggable } from "react-beautiful-dnd";
import { readMenuRestaurant } from "../../redux/actions/restaurantsMenuActions";

const MenuCard = () => {
  const { menu } = useSelector((state) => state.restaurants);
  const restaurantId = useSelector(state => state.restaurants.restaurantId);
  const dispatch = useDispatch();
  const [updateMenu, setUpdateMenu] = useState([]);

  useEffect(() => {
    dispatch(readMenuRestaurant({ restaurantId }));
  }, [dispatch, restaurantId]);

  
  useEffect(() => {
    if (updateMenu.length > 0) {
      dispatch(updateMenu(updateMenu));
    }
  }, [dispatch, updateMenu]);

  const handleDragEnd = (result) => {
    const { destination, source } = result;

    if (!destination) {
      return;
    }

    if (
      destination.droppableId === source.droppableId &&
      destination.index === source.index
    ) {
      return;
    }
    const newSections = Array.from(menu);
    const movedDish = newSections[source.droppableId].sections[
      source.index
    ].dishes.splice(source.index, 1)[0];
    newSections[destination.droppableId].sections[
      destination.index
    ].dishes.splice(destination.index, 0, movedDish);

    setUpdateMenu(newSections);

    // dispatch(updateMenu(source, destination, draggableId));
  };

  

  return (
    <DragDropContext onDragEnd={handleDragEnd}>
      <div className={styles.bigContainer}>
        {menu?.map((section, sectionIndex) => {
          if (!section || !section.sections) {
            return null;
          }

          const hasDishesInSection = section.sections.some(
            (subSection) => subSection?.dishes?.length > 0
          );
          if (!hasDishesInSection) {
            return null;
          }

          return (
            <div className={styles.menu} key={sectionIndex}>
              <h2>{section?.name}</h2>
              {section?.sections?.map((subSection, subSectionIndex) => {
                const hasDishesInSubSection = subSection?.dishes?.length > 0;
                if (!hasDishesInSubSection) {
                  return null;
                }

                return (
                  <Droppable
                    droppableId={`section-${sectionIndex}-subsection-${subSectionIndex}`}
                    key={subSectionIndex}
                  >
                    {(provided) => (
                      <div ref={provided.innerRef} {...provided.droppableProps}>
                        <h3>{subSection?.name}</h3>
                        <ul>
                          {subSection.dishes.map((dish, dishIndex) => (
                            <Draggable
                              key={dishIndex}
                              draggableId={`dish-${sectionIndex}-${subSectionIndex}-${dishIndex}`}
                              index={dishIndex}
                            >
                              {(provided) => (
                                <li
                                  ref={provided.innerRef}
                                  {...provided.draggableProps}
                                  {...provided.dragHandleProps}
                                >
                                  <h4>{dish?.name}</h4>
                                  <p>Description: {dish?.description}</p>
                                  <p>Weight: {dish?.weight}</p>
                                  <p>Price: {dish?.price}</p>
                                  <img
                                    className={styles.imageDish}
                                    src={dish?.imageUrl}
                                    alt="Dish"
                                  />
                                </li>
                              )}
                            </Draggable>
                          ))}
                        </ul>
                        {provided.placeholder}
                      </div>
                    )}
                  </Droppable>
                );
              })}
            </div>
          );
        })}
      </div>
    </DragDropContext>
  );
};
export default MenuCard;
